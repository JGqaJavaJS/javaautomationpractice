package com.javacorepractice.exceptions;

import java.io.FileInputStream;
import java.io.IOException;

public class ExceptionDemo {

    public static void main(String[] args) {
        // ERROR (JVM) — uncomment to see a crash:
        // causeError();

        handleCheckedException();     // checked: must handle or declare
        exceptionWithFinally();       // finally: always runs
        handleUncheckedException();   // unchecked: runtime only

        try {
            exceptionWithThrow();     // rethrow: handled here (higher level)
        } catch (ArithmeticException e) {
            System.out.println("Handled in main: " + e.getMessage());
        }

        // throw: manual exception
        // throwMathException();

        // throws: method declares it may throw
        // throwsMathException();
    }

    // ERROR: StackOverflowError (don’t catch in real apps)
    static void causeError() {
        causeError();
    }

    // CHECKED: try-with-resources closes stream via AutoCloseable
    static void handleCheckedException() {
        try (FileInputStream in = new FileInputStream("non_existing_file.txt")) {
            System.out.println("Opened.");
        } catch (IOException e) {
            System.out.println("Checked caught: " + e);
        }
    }

    // FINALLY: runs whether try fails or not
    static void exceptionWithFinally() {
        try {
            System.out.println("Finally demo start");
            int x = 10 / 0;
            System.out.println("Result: " + x); // skipped
        } catch (ArithmeticException e) {
            System.out.println("Caught: " + e.getMessage());
        } finally {
            System.out.println("Finally: cleanup.");
        }
    }

    // UNCHECKED: ArithmeticException
    static void handleUncheckedException() {
        try {
            int r = 10 / 0;
            System.out.println("Result = " + r); // skipped
        } catch (ArithmeticException e) {
            System.out.println("Unchecked caught: " + e);
        }
    }

    // THROW: manual exception
    static void throwMathException() {
        int b = 0;
        if (b == 0) throw new ArithmeticException("Division by zero is not allowed");
    }

    // THROWS: declares possible exception (unchecked here for demo)
    static void throwsMathException() throws ArithmeticException {
        int r = 10 / 0;
        System.out.println("Never reached: " + r);
    }

    // RETHROW: catches, logs, rethrows (handled in main)
    static void exceptionWithThrow() {
        try {
            int r = 10 / 0;
            System.out.println("Never reached: " + r);
        } catch (ArithmeticException e) {
            System.out.println("Local log: " + e.getMessage());
            throw e; // rethrow upward
        }
    }
}
