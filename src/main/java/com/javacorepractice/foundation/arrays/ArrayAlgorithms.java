package com.javacorepractice.foundation.arrays;

import java.util.Arrays;

public class ArrayAlgorithms {
    public void runAll() {
        int[] arr = {5, 3, 9, 2, 11, 4, 4, 22, 0, 4};
        if (!isArrayNullOrEmpty(arr)) {
            System.out.println("Array: " + Arrays.toString(arr));
            findMinMax(arr);
            sumArray(arr);
            averageArray(arr);
            linearSearch(arr, 3);
            sortArray(arr);
            binarySearch(arr, 4);
            reverseArray(arr);
            removeDuplicatesSorted(arr);
            rotateArray(arr, 2);
            rotateArray2(arr, 2);
        } else {
            System.out.println("Array is null or empty");
        }
    }

    public boolean isArrayNullOrEmpty(int[] arr) {
        // Returns true if array reference is null OR has zero elements
        return arr == null || arr.length == 0;
    }

    // Finds minimum and maximum values in an integer array, O(n)
    public void findMinMax(int[] arr) {
        System.out.println("Method: findMinMax");
        int min = arr[0];
        int max = arr[0];
        int length = arr.length;

        // Single pass through the array — O(n)
        for (int i = 1; i < length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.println("Min: " + min + " Max: " + max);
    }

    // Calculates the sum of all elements in the array, O(n)
    public int sumArray(int[] arr) {
        System.out.println("Method: sumArray");
        int sum = 0;

        // Enhanced for-loop — single pass, O(n)
        for (int number : arr) {
            sum = sum + number;
        }
        System.out.println("Sum of array: " + sum);
        return sum;
    }

    // Calculates the average value of the array
    public void averageArray(int[] arr) {
        System.out.println("Method: averageArray");
        double average = (double) sumArray(arr) / arr.length;
        System.out.println("Average: " + average);
    }

    // Linear search — scans each element until the target is found, O(n)
    public void linearSearch(int[] arr, int target) {
        System.out.println("Method: linearSearch");
        int length = arr.length;
        boolean found = false;
        for (int i = 0; i < length; i++) {
            if (arr[i] == target) {
                System.out.println("Target: " + target + ", Index: " + i);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Target " + target + " not found in array.");
        }
    }

    // Sorts an integer array manually (Bubble Sort) without modifying the original
    public int[] sortArray(int[] arr) {
        System.out.println("Method: sortArray");

        // Create a copy so that the original array remains unchanged
        int[] sorted = Arrays.copyOf(arr, arr.length);
        int length = sorted.length;
        boolean flag;

        // Bubble Sort — repeatedly compares adjacent elements and swaps them
        // O(n²)
        for (int i = 0; i < length - 1; i++) {
            flag = false;
            for (int j = 0; j < length - i - 1; j++) {
                if (sorted[j] > sorted[j + 1]) {
                    int temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                    flag = true;
                }
            }
            // Optimization: if no swaps happened, array is already sorted
            if (!flag) break;
        }

        System.out.println("Sorted ascending (manual): " + Arrays.toString(sorted));
        return sorted; // return the new sorted array
    }


    // Performs binary search on a sorted array (ascending order), O(log n)
    // find first match only
    public void binarySearch(int[] arr, int target) {
        System.out.println("Method: binarySearch");

        // Sort the copy to ensure binary search works correctly
        int[] arrSearch = Arrays.copyOf(arr, arr.length);
        Arrays.sort(arrSearch);

        int left = 0;
        int right = arrSearch.length - 1;

        // Iterative binary search — avoids recursion
        // Each iteration halves the search range
        while (left <= right) {
            // Compute middle index safely to prevent integer overflow
            // Uses unsigned bit shift (>>> 1) instead of /2 to find mid safely and avoid integer overflow.
            int mid = left + ((right - left) >>> 1);

            System.out.println("Checking index " + mid + " (value: " + arrSearch[mid] + ")");

            if (arrSearch[mid] == target) {
                System.out.println("Target " + target + " found at index: " + mid);
                return; // exit from method
            } else if (arrSearch[mid] < target) {
                // Target is larger — search the right half
                left = mid + 1;
            } else {
                // The Target is smaller — search the left half
                right = mid - 1;
            }
        }

        // If the loop finishes, and the target was not found
        System.out.println("Target " + target + " not found in array.");
    }

    // Reverses the array in-place — O(n)
    public void reverseArray(int[] arr) {
        System.out.println("Method: reverseArray");

        int[] arrReverse = Arrays.copyOf(arr, arr.length);
        int left = 0;
        int right = arrReverse.length - 1;

        // Swap symmetric elements until the middle
        while (left < right) {
            int temp = arrReverse[left];
            arrReverse[left] = arrReverse[right];
            arrReverse[right] = temp;

            left++;
            right--;
        }

        System.out.println("Reversed array: " + Arrays.toString(arrReverse));
    }

    // Removes duplicates from a sorted array — O(n)
    public void removeDuplicatesSorted(int[] arr) {
        System.out.println("Method: removeDuplicatesSorted");

        // Work on a copy to avoid mutating original
        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);

        // Temporary buffer for uniques
        int[] temp = new int[sorted.length];
        int write = 0;
        temp[write++] = sorted[0]; // first element always unique

        // Compare neighbors
        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i] != sorted[i - 1]) {
                temp[write++] = sorted[i];
            }
        }

        // Trim extra zeros
        int[] result = Arrays.copyOf(temp, write);
        System.out.println("Sorted array without duplicates: " + Arrays.toString(result));
    }


    // Rotates all array to the right by k positions — O(n)
    public void rotateArray(int[] arr, int k) {
        System.out.println("Method: rotateArray");
        int[] arrRotate = Arrays.copyOf(arr, arr.length);
        int length = arrRotate.length;
        // normalize shift:
//  - if k > length → reduces to equivalent rotation (e.g. k=7, len=5 → k=2)
//  - if k < 0 → converts left rotation to right (e.g. k=-2, len=5 → k=3)
//  - ensures k always in range [0, length-1]
        k = ((k % length) + length) % length;
        if (k == 0) {
            System.out.println("No rotation needed");
            return;
        }

        // Step 1: reverse entire array
        reverse(arrRotate, 0, length - 1);
        // Step 2: reverse first k elements
        reverse(arrRotate, 0, k - 1);
        // Step 3: reverse the remaining elements
        reverse(arrRotate, k, length - 1);

        System.out.println("Array after rotation by " + k + ": " + Arrays.toString(arrRotate));
    }

    // Helper method — reverses elements between two indices (inclusive)
    private void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    // Rotates to the right by k using a buffer copy, O(n)
    public void rotateArray2(int[] arr, int k) {
        System.out.println("Method: rotateArray2");
        int[] arrRotate = Arrays.copyOf(arr, arr.length);
        int length = arrRotate.length;
        k = ((k % length) + length) % length; // normalize (handles k > length and negative k)
        if (k == 0) {
            System.out.println("No rotation needed");
            return;
        }

        int[] tmp = new int[length];
        // 1) tail → head
        System.arraycopy(arrRotate, length - k, tmp, 0, k);
        // 2) head → rest
        System.arraycopy(arrRotate, 0, tmp, k, length - k);

        System.out.println("Array after rotation by " + k + ": " + Arrays.toString(tmp));
    }

}
