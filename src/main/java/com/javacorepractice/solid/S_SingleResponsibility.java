package com.javacorepractice.solid;

/*
 One outer class for the principle;
 nested classes keep responsibilities separate.
 --------------------------------------
 Principle: Single Responsibility (SRP)
 Each class in this file has only ONE reason to change:
 - Pet → stores data only (name, kind).
 - PetReporter → responsible only for creating a report string (formatting info).
 - PetFeeder → responsible only for feeding logic.
 This separation keeps code easier to maintain and avoids mixing logic.
*/
public class S_SingleResponsibility {

    /* DTO: only data */
    static class Pet {
        final String name;
        final String kind;

        Pet(String name, String kind) {
            this.name = name;
            this.kind = kind;
        }
    }

    /* Reporting only */
    static class PetReporter {
        String info(Pet p) {
            return String.format("%s %s", p.kind, p.name);
        }
    }

    /* Feeding only */
    static class PetFeeder {
        String feed(Pet p) {
            return "Feeding " + p.name;
        }
    }

    public static void main(String[] args) {
        Pet p = new Pet("Buddy", "Dog");
        System.out.println(new PetReporter().info(p));
        System.out.println(new PetFeeder().feed(p));
    }
}
