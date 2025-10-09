package com.javacorepractice.oop.abstraction.withinterface;

public class MainInterface {

    public static void main(String[] args) {
        Shape circle = new Circle(2);
        System.out.println(circle.describe() + " → area=" + circle.calculateArea());
        System.out.println(circle.example());
    }
}
