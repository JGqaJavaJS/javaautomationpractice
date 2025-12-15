package com.javacorepractice.foundation;

// hashCode improves lookup performance by distributing keys
// equal objects MUST have equal hashCode, different objects MAY have the same hashCode
// the hashCode speeds up comparison by early rejection if hashCodes are not equal;
// equals() performs final value check

public class HashingBasics {

    public static void main(String[] args) {
        basicHashCodeDemo();
    }

    public static void basicHashCodeDemo() {
        System.out.println("Method: basicHashCodeDemo");

        Object a = new Object();
        Object b = new Object();

        System.out.println("Object a hashCode: " + a.hashCode());
        System.out.println("Object b hashCode: " + b.hashCode());

        /*
        - If a class does NOT override toString(), Object.toString() is used
         - Object.toString() returns: <className>@<hex hashCode>
        */
        System.out.println("Object a toString: " + a.toString());
        System.out.println("Object b toString: " + b.toString());

        // Object.equals() is not overridden here, so equals() behaves the same as ==
        System.out.println("a == b: " + (a == b));
        System.out.println("a.equals(b): " + a.equals(b));

        Object str1 = "Hello";
        Object str2 = new String("Hello");

        System.out.println("String str1 hashCode: " + str1.hashCode());
        System.out.println("String str2 hashCode: " + str2.hashCode());

        /*
        - toString() is called on the actual object type, not on the reference type
        - String overrides toString(), so printing an Object reference to a String prints the text
        */
        System.out.println("Object str1 without toString: " + str1);
        System.out.println("Object str1 with toString: " + str1.toString());

        // Different String objects: '==' compares references (false), equals() compares content (true)
        System.out.println("str1 == str2: " + (str1 == str2));
        System.out.println("str1.equals(str2): " + str1.equals(str2));
        System.out.println("str1.hashCode() == str2.hashCode(): " + (str1.hashCode() == str2.hashCode()));
    }
}