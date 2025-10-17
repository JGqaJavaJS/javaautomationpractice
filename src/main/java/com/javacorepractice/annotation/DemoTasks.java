package com.javacorepractice.annotation;

public class DemoTasks {

    @Run
    public void sayHello() {
        System.out.println("Hello, world!");
    }

    @Run
    public void compute() {
        long sum = 0;
        for (int i = 0; i < 10; i++) {sum += i;}
        System.out.println("Sum = " + sum);
    }

    public void plainMethod() {
        System.out.println("I'm not annotated, won't be called.");
    }
}

