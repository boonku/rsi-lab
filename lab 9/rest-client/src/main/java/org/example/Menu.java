package org.example;

import java.util.Scanner;

public class Menu {
    private static final int OPTIONS_COUNT = 7;
    static final int MENU_EXIT = 7;
    private final Scanner scanner;

    public Menu() {
        scanner = new Scanner(System.in);
    }

    public void printMenu() {
        System.out.println("==== SELECT OPTION ===== ");
        System.out.println("1. Get all people");
        System.out.println("2. Get person by id");
        System.out.println("3. Get people count");
        System.out.println("4. Add new person");
        System.out.println("5. Update person");
        System.out.println("6. Delete person");
        System.out.println("7. Exit");
        System.out.println();
    }

    public int readOption() {
        System.out.print("Enter option: ");
        int option = Integer.parseInt(scanner.nextLine());
        while (option > OPTIONS_COUNT || option <= 0) {
            System.out.print("Input valid option: ");
            option = Integer.parseInt(scanner.nextLine());
        }
        return option;
    }

    public int readPersonID() {
        System.out.print("Enter person id: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public Person readPerson() {
        System.out.print("Enter person id: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter person name: ");
        String name = scanner.nextLine();
        System.out.print("Enter person age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter person email: ");
        String email = scanner.nextLine();
        return new Person(id, name, age, email);
    }
}
