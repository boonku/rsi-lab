package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

import static org.example.Menu.MENU_EXIT;

public class Client {
    private final static String BASE_URL = "http://localhost:8080/api/v1/persons";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private final OkHttpClient httpClient;
    private final Menu menu;
    private final ObjectMapper mapper;

    public Client() {
        this.httpClient = new OkHttpClient();
        this.menu = new Menu();
        mapper = new ObjectMapper();
    }

    public void start() throws IOException {
        menu.printMenu();
        int option = menu.readOption();
        while (option != MENU_EXIT) {
            performAction(option);
            System.out.println();
            menu.printMenu();
            option = menu.readOption();
        }
        System.out.println("Client stopped");
    }

    private void performAction(int option) throws IOException {
        switch (option) {
            case 1 -> listPersons();
            case 2 -> listPersonById();
            case 3 -> listPersonsCount();
            case 4 -> addNewPerson();
            case 5 -> updatePerson();
            case 6 -> deletePerson();
        }
    }

    private void listPersons() throws IOException {
        System.out.println("listing persons");
        Request request = new Request.Builder()
                .url(BASE_URL)
                .build();

        Call call = httpClient.newCall(request);
        Response response = call.execute();
        if (response.isSuccessful()) {
            String body = response.body().string();
            System.out.println(body);
        } else {
            printErrorResponse(response);
        }
    }

    private void listPersonById() throws IOException {
        System.out.println("listing person by id");
        int id = menu.readPersonID();
        Request request = new Request.Builder()
                .url(BASE_URL + "/" + id)
                .build();

        Call call = httpClient.newCall(request);
        Response response = call.execute();
        if (response.isSuccessful()) {
            Person person = mapper.readValue(response.body().string(), Person.class);
            System.out.println(person);
        } else {
            printErrorResponse(response);
        }
    }

    private void listPersonsCount() {
        System.out.println("listing person count");
    }

    private void addNewPerson() throws IOException {
        System.out.println("adding new person");
        Person person = menu.readPerson();
        RequestBody requestBody = getRequestBody(person);
        Request request = new Request.Builder()
                .url(BASE_URL)
                .post(requestBody)
                .build();

        Call call = httpClient.newCall(request);
        Response response = call.execute();
        System.out.println(response.body().string());
    }

    private void updatePerson() throws IOException {
        int id = menu.readPersonID();
        System.out.println("Input person details");
        Person person = menu.readPerson();
        RequestBody requestBody = getRequestBody(person);
        Request request = new Request.Builder()
                .url(BASE_URL + "/" + id)
                .put(requestBody)
                .build();

        Call call = httpClient.newCall(request);
        Response response = call.execute();
        if (response.isSuccessful()) {
            System.out.println(response.body().string());
        } else {
            printErrorResponse(response);
        }
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

    private <T> RequestBody getRequestBody(T object) {
        try {
            return RequestBody.create(JSON, mapper.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            System.out.println("error while processing json");
            throw new RuntimeException(e);
        }
    }

    private void printErrorResponse(Response response) throws IOException {
        ErrorResponse error = mapper.readValue(response.body().string(), ErrorResponse.class);
        System.out.println(error);
    }
}
