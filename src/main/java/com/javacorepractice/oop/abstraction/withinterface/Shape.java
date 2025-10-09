package com.javacorepractice.oop.abstraction.withinterface;

public interface Shape {

    public double calculateArea();

    default String describe() {
        return "Generic Shape";
    }

    default String example() {
        return "example";
    }
}
