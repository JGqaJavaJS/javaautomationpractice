package com.javacorepractice.foundation.arrays;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayBasics {

    public void runAll() {
        arrayBasics();
        arrayIteration();
        arrayCopy();
        arraySort();
        arrayToString();
        compareArrays();
        fillArray();
    }

    // Basic array operations: creation, length, index access,
    // modification, and print
    public void arrayBasics() {
        System.out.println("Method: arrayBasics");
        // Create an array of 10 integers (default values = 0)
        int[] arr = new int[10];
        // Length is fixed at creation time
        System.out.println("Array length: " + arr.length);
        // Access by index (O(1))
        arr[3] = 5;
        System.out.println("Element at index 3: " + arr[3]);
        // Modify element by index (O(1))
        arr[3] = 11;
        System.out.println("Updated element at index 3: " + arr[3]);
        System.out.println("Print array: " + Arrays.toString(arr));
    }

    // Iteration examples: traditional for loop vs enhanced for-each loop
    public void arrayIteration() {
        System.out.println("Method: arrayIteration");

        int[] arr = {0, 1, 2, 3, 4, 9};
        int length = arr.length;

        // Classic for loop — O(n)
        for (int i = 0; i < length; i++) {
            System.out.println("For-loop: index = " + i + ", value = " + arr[i]);
        }

        // Enhanced for-each loop — O(n)
        for (int number : arr) {
            System.out.println("For-each: value = " + number);
        }
    }

    // Copying arrays: System.arraycopy() vs Arrays.copyOf()
    public void arrayCopy() {
        System.out.println("Method: arrayCopy");
        int[] source = {1, 2, 3, 4, 5};

        // System.arraycopy(): copies elements between arrays (O(n))
        int[] target1 = new int[source.length];
        System.arraycopy(source, 0, target1, 0, source.length);
        System.out.println("System.arraycopy result: " + Arrays.toString(target1));

        // Arrays.copyOf(): creates and returns a new array (O(n))
        int[] target2 = Arrays.copyOf(source, source.length);
        System.out.println("Arrays.copyOf result: " + Arrays.toString(target2));

        // Modify source to show independence of copies
        source[0] = 99;
        System.out.println("After modifying source:");
        System.out.println("source:  " + Arrays.toString(source));
        System.out.println("target1: " + Arrays.toString(target1));
        System.out.println("target2: " + Arrays.toString(target2));
    }

    // Sorting arrays: Arrays.sort() and reverse order
    public void arraySort() {
        System.out.println("Method: arraySort");
        int[] source = {7, 2, 9, 11, 1};
        System.out.println("Array before sorting: " + Arrays.toString(source));

        // Ascending sort — O(n log n)
        Arrays.sort(source);
        System.out.println("Array after sorting: " + Arrays.toString(source));

        // Reverse order — O(n)
        int[] reversed = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            reversed[i] = source[source.length - 1 - i];
        }
        System.out.println("Sorted descending: " + Arrays.toString(reversed));

        // Sorting arrays in descending order using Comparator.reverseOrder()
        // Works only with Integer[] (not with primitive int[])
        Integer[] arr = {7, 9, 11, 3, 6};
        System.out.println("Original array: " + Arrays.toString(arr));
        // Sort in descending order — O(n log n)
        Arrays.sort(arr, Comparator.reverseOrder());
        System.out.println("Sorted in descending order: " + Arrays.toString(arr));
    }

    // Converting arrays to strings: Arrays.toString() vs Arrays.deepToString()
    public void arrayToString() {
        System.out.println("Method: arrayToString");

        int[] simpleArray = {1, 2, 3, 4};
        int[][] nestedArray = {{1, 2, 5}, {3, 4}};

        // Arrays.toString() — works for one-dimensional arrays only
        System.out.println("Simple array with Arrays.toString(): "
                + Arrays.toString(simpleArray));

        // Arrays.toString() on multidimensional array prints references
        System.out.println("Nested array with Arrays.toString(): "
                + Arrays.toString(nestedArray));

        // Arrays.deepToString() — recursively converts nested arrays to readable form
        System.out.println("Nested array with Arrays.deepToString(): "
                + Arrays.deepToString(nestedArray));
    }

    // Comparing arrays: Arrays.equals() vs Arrays.deepEquals()
    public void compareArrays() {
        System.out.println("Method: compareArrays");

        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 2, 3};
        int[] arr3 = {1, 2, 4};

        int[][] nested1 = {{1, 2}, {3, 4}};
        int[][] nested2 = {{1, 2}, {3, 4}};
        int[][] nested3 = {{1, 2}, {4, 5}};

        // Arrays.equals(): O(n) — compares primitive values for one-dimensional arrays
        System.out.println("arr1 vs arr2 (equals): " + Arrays.equals(arr1, arr2)); // true
        System.out.println("arr1 vs arr3 (equals): " + Arrays.equals(arr1, arr3)); // false

    /* Arrays.equals() on nested arrays still performs element-by-element comparison (O(n)),
       but each element here is itself an array — an object reference.
       Therefore, it compares the *references* stored in the outer array,
       not the inner array contents.
       In Java, int[][] is not a true 2D array — it is an array of references to int[] arrays.
     */
        System.out.println("nested1 vs nested2 (equals): " + Arrays.equals(nested1, nested2)); // false

        // Arrays.deepEquals(): O(n * m) — recursively compares nested arrays
        System.out.println("nested1 vs nested2 (deepEquals): " + Arrays.deepEquals(nested1, nested2)); // true
        System.out.println("nested1 vs nested3 (deepEquals): " + Arrays.deepEquals(nested1, nested3)); // false
    }

    // Demonstrates Arrays.fill() — full and partial fill
    public void fillArray() {
        System.out.println("Method: fillArray");

        int[] arr = new int[10];
        System.out.println("Initial array: " + Arrays.toString(arr));

        // Arrays.fill(array, value) — fills the entire array with the given value
        // O(n)
        Arrays.fill(arr, 5);
        System.out.println("After full fill with 5: " + Arrays.toString(arr));

        // Arrays.fill(array, fromIndex, toIndex, value)
        // fills the range [fromIndex, toIndex) — end index is exclusive
        // O(k), where k = toIndex - fromIndex
        Arrays.fill(arr, 2, 5, 9);
        System.out.println("After partial fill (2..4) with 9: " + Arrays.toString(arr));

    /* Notes:
       - fill() is efficient — it writes directly into the backing array in a loop.
       - It does not create new objects (works in-place).
       - For reference types, the same object reference is written repeatedly.
     */
    }

}
