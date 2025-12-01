package com.javacorepractice.foundation;

import java.util.List;

public class LambdaExamples {

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6);
        System.out.println("Source list: " + list);

        System.out.println("1. Full lambda: explicit type + block: ");
        list.forEach((Integer x) -> {
            if (x % 2 == 0) {
                System.out.println("Full lambda: " + x);
            }
        });

        System.out.print("2. Short lambda: type inference + block: ");
        list.forEach(x -> System.out.print(x));

        System.out.println();
        System.out.print("3. Method reference (shorthand syntax using ::): ");
        list.forEach(System.out::print);
    }
}
