package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.example.Menu.MENU_EXIT;

public class Client {
    private static final String BASE_URL = "http://localhost:8080/api/v1/persons";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private final OkHttpClient httpClient;
    private final Menu menu;
    private final ObjectMapper mapper;

    public Client() {
        this.httpClient = new OkHttpClient.Builder()
                .callTimeout(5, TimeUnit.SECONDS)
                .build();
        this.menu = new Menu();
        mapper = new ObjectMapper();
    }

    public void start() {
        menu.printMenu();
        int option = menu.readOption();
        while (option != MENU_EXIT) {
            try {
                performAction(option);
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
            menu.printMenu();
            option = menu.readOption();
        }
        System.out.println("Client stopped");
    }

    private void performAction(int option) throws IOException {
        switch (option) {
            case 1 -> listPeople();
            case 2 -> listPersonById();
            case 3 -> listPeopleCount();
            case 4 -> addNewPerson();
            case 5 -> updatePerson();
            case 6 -> deletePerson();
        }
    }

    private void listPeople() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL)
                .build();

        Call call = httpClient.newCall(request);
        Response response = call.execute();
        if (response.isSuccessful()) {
            JsonNode jsonNode = mapper.readTree(response.body().string())
                    .get("_embedded")
                    .get("personList");
            List<Person> people = mapper.readValue(jsonNode.toString(), new TypeReference<List<Person>>() {
            });
            people.forEach(System.out::println);
        } else {
            printErrorResponse(response);
        }
    }

    private void listPersonById() throws IOException {
        int id = menu.readPersonID();
        Request request = new Request.Builder()
                .url(BASE_URL + "/" + id)
                .build();

        Call call = httpClient.newCall(request);
        Response response = call.execute();
        printPersonResponse(response);
    }

    private void listPeopleCount() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL)
                .build();

        Call call = httpClient.newCall(request);
        Response response = call.execute();
        if (response.isSuccessful()) {
            JsonNode jsonNode = mapper.readTree(response.body().string())
                    .get("_embedded")
                    .get("personList");
            List<Person> people = mapper.readValue(jsonNode.toString(), new TypeReference<List<Person>>() {
            });
            System.out.println("People count: " + people.size());
        } else {
            printErrorResponse(response);
        }
    }

    private void addNewPerson() throws IOException {
        Person person = menu.readPerson();
        RequestBody requestBody = createRequestBody(person);
        Request request = new Request.Builder()
                .url(BASE_URL)
                .post(requestBody)
                .build();

        Call call = httpClient.newCall(request);
        Response response = call.execute();
        printPersonResponse(response);
    }

    private void updatePerson() throws IOException {
        int id = menu.readPersonID();
        System.out.println("Enter person details");
        Person person = menu.readPerson();
        RequestBody requestBody = createRequestBody(person);
        Request request = new Request.Builder()
                .url(BASE_URL + "/" + id)
                .put(requestBody)
                .build();

        Call call = httpClient.newCall(request);
        Response response = call.execute();
        printPersonResponse(response);
    }

    private void deletePerson() throws IOException {
        int id = menu.readPersonID();
        Request request = new Request.Builder()
                .url(BASE_URL + "/" + id)
                .delete()
                .build();

        Call call = httpClient.newCall(request);
        Response response = call.execute();
        if (response.isSuccessful()) {
            System.out.println("Deleted person with id: " + id);
        } else {
            printErrorResponse(response);
        }
    }

    private <T> RequestBody createRequestBody(T object) {
        try {
            return RequestBody.create(mapper.writeValueAsString(object), JSON);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void printPersonResponse(Response response) throws IOException {
        if (response.isSuccessful()) {
            Person person = null;
            if (response.body() != null) {
                person = mapper.readValue(response.body().string(), Person.class);
            }
            System.out.println(person);
        } else {
            printErrorResponse(response);
        }
    }

    private void printErrorResponse(Response response) throws IOException {
        ErrorResponse error = null;
        if (response.body() != null) {
            error = mapper.readValue(response.body().string(), ErrorResponse.class);
        }
        System.out.println(error);
    }
}
