package com.javacorepractice.oop.abstraction.withclass;

public class CircleByAbstract extends AbstractShape{

    private final double radius;

    public CircleByAbstract(double radius) {
        super("Circle (r=" + radius + ")");
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}
