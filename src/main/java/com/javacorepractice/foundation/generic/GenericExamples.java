package com.javacorepractice.foundation.generic;

/*
 Generics in Java:
 + Provides type safety at compile time
 + Eliminates many ClassCastException
 + Allows reusable and flexible code

 Limitations:
 - Type erasure removes generic information at runtime
 - No primitives as type parameters (use Integer instead of int)
 - Cannot create arrays of generic types (e.g. new T[])
*/


public class GenericExamples {

    public static void main(String[] args) {

        // Generic method called with different types
        printElement("string");
        printElement(21);

        // Generic class with concrete types
        GenericClass<String> stringBox = new GenericClass<>();
        stringBox.printValue("string");

        GenericClass<Integer> intBox = new GenericClass<>();
        intBox.printValue(22);

        // Bounded generic method (T extends Number)
        printDoubleValue(23);
        printDoubleValue(23.25);
        // error: printDoubleValue("string");

        // Bounded generic method (T extends String)
        printString("string 3");
        // error: printString(24);
    }

    public static <T> void printElement(T element) {
        System.out.println("Method: printElement: " + element);
    }

    public static <T extends Number> void printDoubleValue(T num) {
        System.out.println("Method: printDoubleValue: " + num.doubleValue());
    }

    public static <T extends String> void printString(T str) {
        System.out.println("Method: printString: " + str);
    }

}
