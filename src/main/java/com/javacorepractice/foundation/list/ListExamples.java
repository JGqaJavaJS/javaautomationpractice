package com.javacorepractice.foundation.list;

import java.util.*;

public class ListExamples {

    public void runAll() {
        arrayListDemo();
        linkedListDemo();
        comparePerformance();
    }

    /*
    1. Storage Structure
ArrayList: dynamic array, contiguous memory.
LinkedList: doubly linked nodes, scattered in memory.

2. Access Speed
ArrayList: get(index) is O(1).
LinkedList: get(index) is O(n).

3. Insert/Delete
ArrayList: middle ops O(n); end insert usually O(1).
LinkedList: start/end ops O(1); middle ops O(n).

4. Memory Usage
ArrayList: stores only elements.
LinkedList: each node stores data + two pointers (more memory).
     */

    // Demonstrates ArrayList basics — O(1) access, O(n) insert/remove middle
    public void arrayListDemo() {
        System.out.println("Method: arrayListDemo");

        List<String> list = new ArrayList<>();
        list.add("Alpha");
        list.add("Bravo");
        list.add("Charlie");
        System.out.println("ArrayList: " + list);

        list.add(1, "Inserted"); // O(n) — shifts elements
        System.out.println("After insertion: " + list);

        list.remove("Bravo"); // O(n) — linear search + shift
        System.out.println("After removal: " + list);

        System.out.println("Access index 2: " + list.get(2)); // O(1)
        System.out.println("Contains 'Alpha': " + list.contains("Alpha")); // O(n)

        // sort() — O(n log n)
        list.sort(Comparator.naturalOrder());
        System.out.println("After sort: " + list);

        // toArray() — O(n)
        Object[] array = list.toArray();
        System.out.println("Converted to array: " + Arrays.toString(array));

        // Arrays.asList() — fixed-size list backed by array (O(1))
        List<String> fixedList = Arrays.asList("Delta", "Echo", "Foxtrot");
        //Arrays.asList(array);
        System.out.println("List from Arrays.asList(): " + fixedList);

        // addAll() — O(n)
        list.addAll(fixedList);
        System.out.println("After addAll(): " + list);

        // size() — O(1)
        System.out.println("List size: " + list.size());

        // subList() — O(k) view (not copy), backed by original list
        // Includes element at 'fromIndex', excludes element at 'toIndex'
        List<String> subList = list.subList(1, 4);
        System.out.println("Sublist (1–4): " + subList);

        // forEach() — O(n)
        System.out.print("Iterating with forEach: ");
        // Lambda expression (Java 8+) — concise inline function instead of a full loop
        list.forEach(item -> System.out.print(item + " "));
    }

    // Demonstrates LinkedList as a List — O(1) add/remove at ends, O(n) random access
    public void linkedListDemo() {
        System.out.println("Method: linkedListDemo");

        List<String> list = new LinkedList<>();
        list.add("One");
        list.add("Two");
        list.add("Three");
        System.out.println("LinkedList: " + list);

        list.add(0, "Zero"); // insert at start, O(1)
        list.add(list.size(), "Four"); // insert at end, O(1)
        System.out.println("After insert at positions: " + list);

        list.remove(0); // remove first, O(1)
        list.remove(list.size() - 1); // remove last, O(1)
        System.out.println("After remove at positions: " + list);

        System.out.println("Contains 'Two': " + list.contains("Two")); // O(n)
    }

    // Rough timing comparison (ArrayList vs LinkedList)
    public void comparePerformance() {
        System.out.println("Method: comparePerformance");

        int iterations = 500_000;
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        long start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) arrayList.add(i);
        long end = System.currentTimeMillis();
        System.out.println("ArrayList fill all: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) linkedList.add(i);
        end = System.currentTimeMillis();
        System.out.println("LinkedList fill all: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        arrayList.remove(iterations / 2); // O(n)
        end = System.currentTimeMillis();
        System.out.println("ArrayList remove middle: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        linkedList.remove(iterations / 2); // O(n)
        end = System.currentTimeMillis();
        System.out.println("LinkedList remove middle: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        arrayList.add(iterations / 2, 5); // O(n)
        end = System.currentTimeMillis();
        System.out.println("ArrayList add middle: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        linkedList.add(iterations / 2, 5); // O(n)
        end = System.currentTimeMillis();
        System.out.println("LinkedList add middle: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {
            arrayList.add(0, -1);
            arrayList.remove(0);
        }
        end = System.currentTimeMillis();
        System.out.println("ArrayList add/remove head: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {
            linkedList.add(0, -1);
            linkedList.remove(0);
        }
        end = System.currentTimeMillis();
        System.out.println("LinkedList add/remove head: " + (end - start) + " ms");
    }

}
