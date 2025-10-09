package com.javacorepractice.oop.abstraction.withclass;

public class MainAbstractionClass {

    public static void main(String[] args) {
        AbstractShape shape = new CircleByAbstract(2.0);
        System.out.println(shape.describe() + " → area=" + shape.calculateArea());
    }
}
