package com.javacorepractice.foundation;

// Set stores unique elements; duplicates are removed based on equals() and hashCode()
// HashSet does NOT guarantee any iteration order
// LinkedHashSet guarantees insertion order
// TreeSet guarantees sorted order


import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetExamples {

    public static void main(String[] args) {
        hashSetDemo();
        linkedHashSetDemo();
        treeSetDemo();
    }

    public static void hashSetDemo() {
        System.out.println("Method: hashSetDemo");

        // HashSet stores unique elements, order is NOT guaranteed
        Set<Integer> set = new HashSet<>();

        set.add(3);
        set.add(1);
        set.add(2);
        set.add(2); // duplicate is ignored
        set.add(1); // duplicate is ignored

        // Iteration order is unpredictable
        for (Integer value : set) {
            System.out.println(value);
        }

        /*
         Characteristics:
         - Only unique elements
         - No guaranteed order
         - Average O(1) add/contains/remove
         - Uniqueness is based on equals() and hashCode()
        */
    }

    public static void linkedHashSetDemo() {
        System.out.println("Method: linkedHashSetDemo");

        // LinkedHashSet preserves insertion order
        Set<String> set = new LinkedHashSet<>();

        set.add("C");
        set.add("A");
        set.add("B");
        set.add("A"); // duplicate ignored

        // Iteration shows insertion order
        for (String value : set) {
            System.out.println(value);
        }

        /*
         Characteristics:
         - Unique elements
         - Preserves insertion order
         - Slightly more memory than HashSet
        */
    }

    public static void treeSetDemo() {
        System.out.println("Method: treeSetDemo");

        // TreeSet stores unique elements in sorted order
        Set<Integer> set = new TreeSet<>();

        set.add(3);
        set.add(1);
        set.add(2);
        set.add(2); // duplicate ignored

        // Iteration shows sorted order
        for (Integer value : set) {
            System.out.println(value);
        }

        TreeSet<Integer> tree = (TreeSet<Integer>) set;

        System.out.println("First: " + tree.first());
        System.out.println("Last: " + tree.last());
        System.out.println("Higher than 1: " + tree.higher(1));
        System.out.println("Lower than 3: " + tree.lower(3));

        /*
         Characteristics:
         - Unique elements
         - Sorted by natural order or Comparator
         - O(log n) add/contains/remove
         - No null elements allowed
        */
    }
}

