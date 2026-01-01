package com.javacorepractice.foundation;

import java.util.*;

public class QueueExamples {

    public static void main(String[] args) {
        linkedListAsQueueDemo();
        arrayQueueDemo();
        arrayDequeDemo();
        priorityQueueDemo();
    }

    public static void linkedListAsQueueDemo() {
        System.out.println("Method: linkedListAsQueueDemo");

        /*
        Same class, different behavior depending on the interface
        List<String> list = new LinkedList<>();
        */
        Queue<String> queue = new LinkedList<>();

        // Using LinkedList as a Queue (FIFO)
        queue.offer("A");
        queue.offer("C");
        queue.offer("B");

        // Queue allows adding only to the tail and removing only from the head, but iteration can read all elements
        System.out.println("peek, get the head: " + queue.peek()); // all queue A,C,B
        System.out.println("poll, remove the head: " + queue.poll()); // all queue C,B
        System.out.println("peek, get the new head: " + queue.peek()); // all queue C,B
        System.out.println("offer, add the element in the tail: " + queue.offer("T")); // all queue C,B,T

        System.out.println("Remaining queue elements:");
        for (String s : queue) {
            System.out.println(s);
        }

        /*
         Characteristics:
         - LinkedList implements both List and Queue
         - FIFO behavior when used via Queue interface
         - Slower than ArrayDeque for queue operations
        */
    }

    public static void arrayQueueDemo() {
        System.out.println("Method: arrayQueueDemo");

        // ArrayDeque is the preferred Queue implementation
        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(1);
        queue.offer(3);
        queue.offer(2);
        for (Integer a : queue) {
            System.out.println(a);
        }

        System.out.println("Remove the head: " + queue.poll());
        System.out.println("Add the tail: " + queue.offer(11));
        for (Integer a : queue) {
            System.out.println(a);
        }

        /*
         Queue interface:
         - add - offer only to the tail
         - remove - poll only from the head
         - FIFO only
        */
    }

    public static void arrayDequeDemo() {
        System.out.println("Method: arrayDequeDemo");

        Deque<Integer> deque = new ArrayDeque<>();

        // add to tail
        deque.offerLast(1);   // [1]
        deque.offerLast(2);   // [1, 2]
        deque.offerLast(3);   // [1, 2, 3]

        // remove from head (FIFO)
        deque.pollFirst();    // removes 1 -> [2, 3]

        // add to head
        deque.offerFirst(10); // [10, 2, 3]

        // remove from tail
        deque.pollLast();     // removes 3 -> [10, 2]

        System.out.println("Final deque: " + deque); // [10, 2]

        /*
         Summary:
         - offerLast  -> add to tail
         - pollFirst  -> remove from head
         - offerFirst -> add to head
         - pollLast   -> remove from tail
        */
    }

    public static void priorityQueueDemo() {
        System.out.println("Method: priorityQueueDemo");

        // PriorityQueue orders elements by priority, NOT by insertion order
        Queue<Integer> queue = new PriorityQueue<>();
        // reverseOrder defines max-heap behavior (largest value has the highest priority)
        // Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());

        queue.offer(5); // [5]
        queue.offer(1); // [1,5]
        queue.offer(3); // [1,5,3]
        queue.offer(2); // [1,2,3,5]
        queue.offer(2); // duplicates are allowed [1,2,2,3,5]

        // poll() always removes the element with the highest priority (smallest value here)
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }

        /*
         Key points:
         - PriorityQueue is NOT FIFO
         - Elements are ordered by priority (natural order by default)
         - Duplicates ARE allowed
         - poll() returns the smallest element first
         - O(log n) for offer() and poll()
        */
    }

}

