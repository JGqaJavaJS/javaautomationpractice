package com.javacorepractice.annotation;

import java.lang.reflect.Method;

public class Runner {
    public static void main(String[] args) throws Exception {
        DemoTasks obj = new DemoTasks();

        for (Method m : obj.getClass().getDeclaredMethods()) {
            if (m.isAnnotationPresent(Run.class)) {
                System.out.println("Running: " + m.getName());
                m.invoke(obj); // run method
            }
        }
    }
}
