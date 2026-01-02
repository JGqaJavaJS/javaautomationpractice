package com.javacorepractice.foundation;

import java.util.*;
import java.util.stream.Collectors;

// Stream relies on lambda expressions
public class StreamExamples {

    public static void main(String[] args) {
        basicPipelineDemo();
        reduceDemo();
        uniqueElementsDemo();
        listToMapDemo();
        groupingDemo();
        flatMapDemo();
    }

    // filter + map + forEach (many -> many)
    public static void basicPipelineDemo() {
        System.out.println("Method: basicPipelineDemo");

        List<Integer> list = List.of(1, 2, 3, 4, 5, 6);

        list = list.stream()
                .filter(x -> x % 2 == 0)
                .map(x -> x * 10)
                .collect(Collectors.toList());

        list.stream().forEach(System.out::println);

        /*
         map() is used for data transformation;
         if the value is only printed, the calculation can be done in forEach
        */


        list.stream()
                .filter(x -> x % 2 == 0)
                .forEach(x -> System.out.println(x * 10));
    }

    // reduce / sum (many -> one)
    public static void reduceDemo() {
        System.out.println("Method: reduceDemo");

        List<Integer> list = List.of(1, 2, 3, 4);

        int sum = list.stream()
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("Sum: " + sum);
    }

    // make collection unique (List -> Set)
    public static void uniqueElementsDemo() {
        System.out.println("Method: uniqueElementsDemo");

        List<Integer> list = List.of(1, 2, 2, 3, 3, 3, 4);

        Set<Integer> unique = list.stream()
                .distinct() // delete duplicates
                .collect(Collectors.toSet()); // delete duplicates

        System.out.println("Unique set: " + unique);

        System.out.println("Not unique list: " + list);
        list = list.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Unique list: " + list);
    }

    // List -> Map
    public static void listToMapDemo() {
        System.out.println("Method: listToMapDemo");

        List<String> list = List.of("one", "two", "three");

        Map<Integer, String> map = list.stream()
                .collect(Collectors.toMap(
                        String::length, // keyMapper: use string length as the map key
                        s -> s, // valueMapper: use the string itself as the value
                        (a, b) -> a // merge function: if key already exists, keep the existing value
                ));


        System.out.println(map);
    }

    // grouping (many -> Map<key, List<values>>)
    public static void groupingDemo() {
        System.out.println("Method: groupingDemo");

        List<String> list = List.of("apple", "apricot", "banana", "Blueberry");

        Map<Character, List<String>> groups = list.stream()
                .collect(Collectors.groupingBy(
                        s -> Character.toLowerCase(s.charAt(0))
                ));
        /*
         groups elements by the first character of the string
         s is the current string in the stream;
         s.charAt(0) takes its first character (index 0) and uses it as the grouping key
        */

        System.out.println(groups);
    }

    // flatMap (many lists -> one list)
    public static void flatMapDemo() {
        System.out.println("Method: flatMapDemo");

        List<List<Integer>> lists = List.of(
                List.of(1, 2),
                List.of(3, 4),
                List.of(5)
        );

        System.out.println("Lists: " + lists);

        List<Integer> list = lists.stream()
                .flatMap(List::stream) // flatten all inner lists into a single Stream<Integer>
                .collect(Collectors.toList());

        System.out.println("List: " + list);
    }
}
