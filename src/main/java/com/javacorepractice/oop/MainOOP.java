package com.javacorepractice.oop;

public class MainOOP {

    public static void main(String[] args) {
        User regular = new RegularUser(1, "user@example.com", "1234");
        User admin = new AdminUser(2, "admin@example.com", "adminpass");

        /*
        Note:
        In real-world projects, System.out.println() is not used for output.
        Logic for working with objects should be placed either in:
        1) a dedicated utility class for object operations, or
        2) the domain class itself if it is not a pure data model (DTO).
        Here it remains inside MainOOP only for demonstration purposes.
        */

        printUserRole(regular);
        printUserRole(admin);

        // Manual casting used intentionally to demonstrate polymorphism only.

        ((AdminUser) admin).printEmail();

        /*
        Calling an overridden method — this is a safer and cleaner approach
        than manual casting, as it relies on polymorphism directly.
        */

        admin.printId();

        printUserRole(admin, true);
    }

    private static void printUserRole(User user) {
        System.out.println(user.getEmail() + " → " + user.getRole());
    }

    private static void printUserRole(User user, boolean showId) {
        if (showId) {
            System.out.println("ID: " + user.getId() + ", Role: " + user.getRole());
        } else {
            printUserRole(user);
        }
    }

}
