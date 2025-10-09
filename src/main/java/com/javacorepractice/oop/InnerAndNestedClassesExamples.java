package com.javacorepractice.oop;

public class InnerAndNestedClassesExamples {

    /*
    Highlighted in yellow because IntelliJ marks it as an unused field.
    Kept intentionally for demonstration purposes — these fields are used
    to illustrate the difference between inner and static nested classes.
     */

    private String instanceField = "Outer instance field";
    private static String staticField = "Outer static field";

    // Inner class — tied to instance of InnerAndNestedClassesExamples
    public class InnerClass {
        public void showAccess() {
            System.out.println("Accessing from InnerClass:");
            System.out.println("Instance field → " + instanceField);
            System.out.println("Static field → " + staticField);
        }
    }

    // Static nested class — no access to instance members
    public static class StaticNestedClass {
        public void showAccess() {
            System.out.println("Accessing from StaticNestedClass:");
            // System.out.println(instanceField); Not allowed
            System.out.println("Static field → " + staticField);
        }
    }

    public static void main(String[] args) {

        // Create static nested class — does NOT require instance
        InnerAndNestedClassesExamples.StaticNestedClass nested
                = new InnerAndNestedClassesExamples.StaticNestedClass();
        nested.showAccess();

        // Create inner class — requires instance of OuterClass
        InnerAndNestedClassesExamples outer = new InnerAndNestedClassesExamples();
        InnerAndNestedClassesExamples.InnerClass inner = outer.new InnerClass();
        inner.showAccess();
    }
}
