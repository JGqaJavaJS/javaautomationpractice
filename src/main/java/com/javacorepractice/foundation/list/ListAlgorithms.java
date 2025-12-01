package com.javacorepractice.foundation.list;

import java.util.*;

public class ListAlgorithms {
    public void runAll() {
        List<Integer> list = fillList(30);
        printWithIterator(list);
        indexOf(list);
        removeElement(list);
        reverseList(list);
        sortList();
    }

    public List<Integer> fillList(int elements) {
        List<Integer> list = new LinkedList<>();
        // O(n): filling the list
        for (int i = 0; i < elements; i++) {
            list.add(i);
        }
        return list;
    }

    public void printWithIterator(List<Integer> list) {
        System.out.println("Method: printWithIterator");

        // O(n): iterating through the list with Iterator
        Iterator<Integer> it = list.iterator();

        System.out.print("Print all elements with Iterator: ");
        while (it.hasNext()) {
            int element = it.next();
            System.out.print(element + " ");
        }
        System.out.println("");
    }

    public void indexOf(List<Integer> list) {
        System.out.println("Method: indexOf");

        // O(n): iterating through the list with Iterator
        Iterator<Integer> it = list.iterator();

        int element = 7;
        int index = 0;

        while (it.hasNext()) {
            if (element == it.next()) {
                System.out.println("Index of element: " + index);
                break;
            }
            index++;
        }
        /*
 In real code also add:
 - not-found handling;
 - type safety (avoid raw types);
 - null checks.
*/
    }

    public void removeElement(List<Integer> list) {
        System.out.println("Method: removeElement");

        // O(n): iterating through the list with Iterator
        Iterator<Integer> it = list.iterator();

        int element = 7;
        while (it.hasNext()) {
            if (element == it.next()) {
                it.remove();
                System.out.println("Element deleted");
                break;
            }
        }
        printWithIterator(list);
    }

    public void reverseList(List<Integer> list) {
        System.out.println("Method: reverseList");

        ListIterator<Integer> head = list.listIterator();
        ListIterator<Integer> tail = list.listIterator(list.size());

        int a, b = 0;

        // O(n): two iterators move toward the middle and swap elements pair-by-pair
        while (head.nextIndex() < tail.previousIndex()) {
            a = head.next();
            b = tail.previous();
            head.set(b);
            tail.set(a);
        }
        printWithIterator(list);
    }

    public void sortList() {
        System.out.println("Method: sortList");
        List<Integer> list = new LinkedList<>(List.of(1, 9, 3, 15, 2));
        System.out.println("List before sorting:");
        printWithIterator(list);

        // O(n²): classic insertion sort
        for (int i = 1; i < list.size(); i++) {
            int key = list.get(i);
            int j = i - 1;

            // shift all elements bigger than key to the right
            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j--;
            }

            // insert key in the correct position
            list.set(j + 1, key);
        }

        System.out.println("List after sorting:");
        printWithIterator(list);
    }

}
