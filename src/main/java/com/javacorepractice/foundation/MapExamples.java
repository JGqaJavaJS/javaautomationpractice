package com.javacorepractice.foundation;

/*
 Most commonly used Map implementations in Java:
 1) HashMap
    Unordered, fast put/get (O(1) on average), allows one null key and null values.
 2) LinkedHashMap
    Maintains insertion order (or access order), slightly slower than HashMap, allows nulls.
 3) TreeMap
    Sorted by key (natural order or Comparator), O(log n), does not allow null keys.
*/

import java.util.*;

public class MapExamples {

    public static void main(String[] args) {
        hashMapDemo();
        hashMapDemo2();
        linkedHashMapDemo();
        linkedHashMapLruDemo();
        treeMapDemo();
    }

    public static void hashMapDemo() {
        System.out.println("Method: hashMapDemo");

        // Create HashMap
        Map<Integer, String> map = new HashMap<>();

        /*
        How HashMap works (simplified):
        1) Key.hashCode() is calculated
        2) Hash is used to choose a bucket (array index)
        3) equals() is used only inside the bucket to find the exact key
        This is why get/put are O(1) on average.
        */

        // Put elements (unordered): key and value
        // Same key overwrite example
        map.put(3, "three");
        System.out.println("Key 3 before overwrite: " + map.get(3));
        map.put(3, "Sun"); // same key, new value
        System.out.println("Key 3 after overwrite: " + map.get(3));

        map.put(1, "one");
        map.put(2, "two");
        map.put(null, "null-key"); // HashMap allows one null key

        // Add multiple entries at once
        map.putAll(Map.of(
                4, "four",
                5, "five",
                6, "six"
        ));

        // print the map
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // Get elements
        System.out.println("Get key 2: " + map.get(2));
        System.out.println("Contains key 1: " + map.containsKey(1));

        // Iteration using entrySet and get key by value
        Integer key = null;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if ("four".equals(entry.getValue())) {
                key = entry.getKey();
                break;
            }
        }

        System.out.println("Key for value 'four': " + key);

        /*
        Characteristics:
        - No guaranteed order
        - Average O(1) put/get (hashCode selects bucket)
        - equals() resolves collisions inside the same bucket
        */
    }

    public static void hashMapDemo2() {
        System.out.println("Method: hashMapDemo2");

        // 1) Counter pattern (getOrDefault)
        Map<String, Integer> counter = new HashMap<>();
        counter.put("apple", counter.getOrDefault("apple", 0) + 1);
        counter.put("apple", counter.getOrDefault("apple", 0) + 1);
        System.out.println("Counter apple: " + counter.get("apple"));

        // 2) Grouping pattern (computeIfAbsent)
        Map<String, List<String>> groups = new HashMap<>();
        groups.computeIfAbsent("A", k -> new ArrayList<>()).add("Alice");
        // if no List exists for key "A", a new ArrayList is created and used as the value
        groups.computeIfAbsent("A", k -> new ArrayList<>()).add("Alice");
        // k is a lambda parameter (can be any name); represents the missing key
        groups.computeIfAbsent("A", k -> new ArrayList<>()).add("Andrew");
        groups.computeIfAbsent("B", k -> new ArrayList<>()).add("Bob");
        System.out.println("Group A: " + groups.get("A"));

        // 3) Accumulate / append pattern
        Map<Integer, String> text = new HashMap<>();
        text.put(3, "Sun");
        // Manual get + put (NOT safe if key is absent: may cause NullPointerException)
        text.put(3, text.get(3) + " Moon1");
        System.out.println("Key 3 after manual append: " + text.get(3));
        // merge() is safe: handles missing key and avoids extra get()
        text.merge(3, " Moon2", String::concat);
        System.out.println("Key 3 after merge: " + text.get(3));
        text.merge(4, " one", String::concat);
        System.out.println("Key 4 after safety merge: " + text.get(4));
    }

    public static void linkedHashMapDemo() {
        System.out.println("Method: linkedHashMapDemo");

        // LinkedHashMap keeps a predictable iteration order (insertion or access order),
        // while HashMap does not guarantee any order

        // LinkedHashMap uses extra memory and is slightly slower than HashMap
        // because it maintains a doubly linked list (before/after) for ordering

        // LinkedHashMap in access-order mode: get() moves entry to the end
        // initialCapacity = 16 (default size), loadFactor = 0.75 (resize threshold),
        // accessOrder = true (reorder entries on access instead of insertion)
        Map<Integer, String> map = new LinkedHashMap<>(16, 0.75f, true);

        // Put elements in a specific order
        map.put(3, "three");
        map.put(1, "one");
        map.put(2, "two");
        map.put(4, "four");

        // In access-order mode, both get() and put(existingKey) move entries to the end
        map.get(2); // access key 2 to move it to the end (access-order)

        // Iteration shows insertion order
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        map.put(5, "five");
        // new entry is added at the end; insertion does not reorder existing entries
        System.out.println("-----");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

    }

    public static void linkedHashMapLruDemo() {
        System.out.println("Method: linkedHashMapLruDemo");

        // LinkedHashMap with access-order enabled (LRU behavior)
        Map<Integer, String> cache = new LinkedHashMap<>(16, 0.75f, true) {

            // Called after each put(); returns true -> the eldest entry is removed
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return size() > 3; // limit cache size to 3 entries
            }
        };

        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three");

        cache.get(1); // access key 1 -> marks it as recently used
        // then order: 2 -> two / 3 -> three / 1 -> one
        cache.put(4, "four"); // size becomes 4 -> first (least recently used) entry is removed

        // Shows LRU result: oldest unused entry is gone
        for (Map.Entry<Integer, String> entry : cache.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public static void treeMapDemo() {
        System.out.println("Method: treeMapDemo");

        // TreeMap keeps keys sorted (natural order or Comparator)
        // put/get are O(log n), unlike HashMap average O(1)
        //Map<Integer, String> map = new TreeMap<>(); - natural order
        // TreeMap with reverse (descending) key order
        Map<Integer, String> map = new TreeMap<>(Comparator.reverseOrder());

        // TreeMap does NOT allow null keys
        // map.put(null, "null-key"); // NullPointerException

        // Put elements in random order
        map.put(3, "three");
        map.put(1, "one");
        map.put(2, "two");
        map.put(5, "five");
        map.put(4, "four");

        // Iteration shows sorted by key
        System.out.println("Sorted by key:");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // Sorting entries in a separate List to avoid modifying the original Map
        TreeMap<Integer, String> tree = (TreeMap<Integer, String>) map;

        System.out.println("firstKey: " + tree.firstKey());
        System.out.println("lastKey: " + tree.lastKey());

        System.out.println("lowerKey(3): " + tree.lowerKey(3));   // key < 3
        System.out.println("higherKey(3): " + tree.higherKey(3)); // key > 3

        System.out.println("headMap(4): " + tree.headMap(4));     // keys < 4
        System.out.println("tailMap(4): " + tree.tailMap(4));     // keys >= 4
        System.out.println("subMap(5, 2): " + tree.subMap(5, 2)); // 5..2 in reverse order
        // reverse order comparator is used
        // in natural order this would correspond to: 2 < key <= 5

        // Values may be duplicated, but keys are always unique in any Map
    }

}
