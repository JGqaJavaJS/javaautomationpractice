package com.javacorepractice.solid;

/*
 Additional example related to SOLID_Enum_Notes (see src/main/java/com/javacorepractice/solid/SOLID_Enum_Notes):
 Demonstrates enums with parameters, constructors, and methods.
 Each enum constant is a named instance (object) of the enum type,
 showing encapsulation and the Open/Closed Principle in action.
 Complements O_OpenClosed with a more data-rich form of enum usage.
*/

public class EnumWithParamsExample {

    enum Animal {
        DOG("Dog", 4, "Woof"),
        CAT("Cat", 4, "Meow"),
        PARROT("Parrot", 2, "Squawk");

        private final String name;
        private final int legs;
        private final String sound;

        Animal(String name, int legs, String sound) {
            this.name = name;
            this.legs = legs;
            this.sound = sound;
        }

        public String getName() {
            return name;
        }

        public int getLegs() {
            return legs;
        }

        public String getSound() {
            return sound;
        }

        public String describe() {
            return String.format("%s has %d legs and says '%s'", name, legs, sound);
        }
    }

    public static void main(String[] args) {
        for (Animal a : Animal.values()) {
            System.out.println(a.describe());
        }
    }
}