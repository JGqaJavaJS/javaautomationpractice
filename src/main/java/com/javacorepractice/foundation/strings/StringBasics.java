package com.javacorepractice.foundation.strings;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringJoiner;

public class StringBasics {

    StringAlgorithms stringAlgorithms = new StringAlgorithms();

    public void runAll() {
        stringEquality();
        stringImmutable();
        stringFormatter();
        compareConcatPerformance();
        splitBySpace("one  two  three");
        splitBySpaceRegex("one  two  three");
        cleanText("Hello,,,       world!!! This is a test...");
        String text = "İstanbul"; // Turkish capital İ (with dot above)
        toLowerEn(text); // İstanbul
        toLowerTr(text); // istanbul
        equalsIgnoreAccents("jalapeño", "jalapeno");
    }

    // String comparison and immutability demo.
    public void stringEquality() {
        System.out.println("Method: stringEquality");
        // O(1) — simple reference and value comparisons (constant time)
        String a = "Example";
        String b = new String("Example");
        // create a new String instance (different reference)
        String c = "example"; // lowercase 'e'

        System.out.println("==: " + (a == b)); // false, compare links
        System.out.println("equals: " + a.equals(b)); // true, compares value
        System.out.println("equals: Example and example: " + a.equals(c)); // false
        System.out.println("equalsIgnoreCase: Example and example: "
                + a.equalsIgnoreCase(c)); // true

        b = b.intern();
        System.out.println("After intern: " + (a == b)); // true
        /* The intern() method adds the string to the JVM string pool
        (or returns the existing pooled reference).
         After calling intern(), both 'a' and 'b' refer to the
         same pooled string object, so (a == b) becomes true.
         */
    }

    // demonstrate, that String class is immutable
    public void stringImmutable() {
        System.out.println("Method: stringImmutable");
        // O(1) — new object creation and reference comparison
        String a = "hello";
        int hash1 = System.identityHashCode(a);
        a = "hello there"; // change object value and create a new object
        int hash2 = System.identityHashCode(a);
        System.out.println("Same identity hash: " + (hash1 == hash2));
    }

    /*
String formatting comparison:
%s-> String, %c -> char, %b -> boolean, %% -> %, %d -> int, %f -> float,
%.2f -> when 3.14159 - 3.14
*/
    public void stringFormatter() {
        System.out.println("Method: stringFormatter");
        String str = "Hello";
        char ch = 'A';
        int i = 10;
        float f = 1.14169F;

        // "+" — concatenation: O(n²) if used in loops (creates new String each time)
        System.out.println("concatenation: " + str + " " + ch + " " + i + "% " + f);

        // String.format() — O(n): single pass to build formatted string
        String strFormatted = String.format("StringFormat: %s %c %d%% %.2f", str, ch, i, f);
        System.out.println(strFormatted);

        // printf() — O(n): formats and prints directly, no new String created
        System.out.printf("printf: %s %c %d%% %.2f", str, ch, i, f);
        System.out.println();

        // String.join() — O(n): iterates once over elements to build result
        String[] names = {"Alice", "Bob", "Charlie"};
        String result = String.join(", ", names);
        System.out.println("Joined string: " + result);
    }

    /* Concatenation: + vs StringBuilder vs StringBuffer vs StringJoiner
    results:
    concatenation: 1514 ms
    StringBuilder: 3 ms
    StringBuffer: 3 ms
    StringJoiner: 5 ms
    StringJoiner (without String.valueOf): 3 ms
     */
    public void compareConcatPerformance() {
        System.out.println("Method: compareConcatPerformance");
        int iterations = 100_000;
        long start;
        long end;

        // concatenation: "+" creates new String each time (O(n²))
        start = System.currentTimeMillis();
        String res1 = "";
        for (int i = 0; i < iterations; i++) {
            res1 = res1 + i;
        }
        end = System.currentTimeMillis();
        System.out.println("concatenation: " + (end - start) + " ms");

        // StringBuilder is preferred for heavy concatenation (O(n))
        start = System.currentTimeMillis();
        StringBuilder res2 = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            res2.append(i);
        }
        end = System.currentTimeMillis();
        System.out.println("StringBuilder: " + (end - start) + " ms");

        // StringBuffer is thread-safe (O(n))
        start = System.currentTimeMillis();
        StringBuffer res3 = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            res3.append(i);
        }
        end = System.currentTimeMillis();
        System.out.println("StringBuffer: " + (end - start) + " ms");

        // StringJoiner joins strings efficiently with delimiter (O(n))
        start = System.currentTimeMillis();
        StringJoiner res4 = new StringJoiner(",");
        for (int i = 0; i < iterations; i++) {
            res4.add(String.valueOf(i));
        }
        end = System.currentTimeMillis();
        System.out.println("StringJoiner: " + (end - start) + " ms");

        // StringJoiner joins strings efficiently with delimiter and without String.valueOf (O(n))
        start = System.currentTimeMillis();
        StringJoiner res5 = new StringJoiner(",");
        for (int i = 0; i < iterations; i++) {
            res5.add("a");
        }
        end = System.currentTimeMillis();
        System.out.println("StringJoiner (without String.valueOf): " + (end - start) + " ms");
    }


    // Split examples (regex vs non-regex) — O(n)
    // Split examples — both calls use regex under the hood.
    // Demonstrates pitfall: split(" ") keeps empty tokens on multiple spaces
    public void splitBySpace(String str) {
        System.out.println("Method: splitBySpace");
        if (stringAlgorithms.isNotEmpty(str)) {
            System.out.println("Before split: " + str);
            String[] arr = str.split(" ");
            System.out.println("After split: " + Arrays.toString(arr));
            // After split: [one, , two, , three]
        }
    }

    public void splitBySpaceRegex(String str) {
        System.out.println("Method: splitBySpaceRegex");
        if (stringAlgorithms.isNotEmpty(str)) {
            System.out.println("Before split: " + str);
            String[] arr = str.split("\\s+");
            System.out.println("After split with regex: " + Arrays.toString(arr));
            // After split with regex: [one, two, three]
        }
    }

    // Cleaning: trim + collapse spaces + remove punctuation, O(n)
    public void cleanText(String str) {
        System.out.println("Method: cleanText");
        System.out.println("String before changes: " + str);
        String res = str.trim()
                .replaceAll("\\s+", " ")
                .replaceAll("\\p{Punct}", "");
        /*
        trim()  removes leading and trailing whitespace from the string 's'
        .replaceAll("\\s+", " ") replaces all sequences of one or more whitespace characters
        (spaces, tabs, newlines) with a single space
        .replaceAll("\\p{Punct}", "") removes all punctuation characters
        (anything that matches the Unicode punctuation class)
         */
        System.out.println("String after changes: " + res);
    }

    // Case & Locale (Turkish 'i' pitfall)
    public void toLowerEn(String str) {
        System.out.println("Method: toLowerEn");
        System.out.println("Before toLowerCase: " + str);
        String res = str.toLowerCase(Locale.ENGLISH);
        System.out.println("After toLowerCase: " + res);
    }

    public void toLowerTr(String str) {
        System.out.println("Method: toLowerTr");
        System.out.println("Before toLowerCase: " + str);
        String res = str.toLowerCase(new Locale("tr"));
        System.out.println("After toLowerCase: " + res);
    }

    // Accent-insensitive compare — removes diacritics before comparison
    public void equalsIgnoreAccents(String a, String b) {
        System.out.println("Method: equalsIgnoreAccents");
        System.out.println("Original: [" + a + "] vs [" + b + "]");
        String normA = Normalizer.normalize(a, Normalizer.Form.NFD).replaceAll("\\p{M}+", "");
        String normB = Normalizer.normalize(b, Normalizer.Form.NFD).replaceAll("\\p{M}+", "");
        boolean result = normA.equalsIgnoreCase(normB);
        System.out.println("After removing accents: [" + normA + "] vs [" + normB + "]");
        System.out.println("Equal ignoring accents: " + result); // true
    }
}
