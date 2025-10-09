package com.javacorepractice.oop.abstraction.withclass;

public abstract class AbstractShape {

    protected final String shapeType;

    protected AbstractShape(String label) {
        this.shapeType = label;
    }

    public abstract double calculateArea();

    public String describe() {
        return shapeType;
    }
}
