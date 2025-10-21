package com.javacorepractice.foundation.strings;

import java.util.StringJoiner;

public class StringAlgorithms {

    public void runAll() {
        reverseManual("123456789");
        reverseWithBuilder("123456789");
        reverseWordsManual("one two three four five");
        reverseWordsStrBuilder("one two three four five");
        reverseWordsJoiner("one two three four five");
        isPalindrome("12344321"); // true
        isPalindrome("1233421"); // false
        removeDuplicateChars("banana");
        collapseAdjacentRuns("aaabbcdddaaa");
        indexOfNaiveAll("abracabradabra", "bra");
        indexOfNaiveAll("hello", "world");
    }

    public boolean isNotEmpty(String str) {
        if (str == null || str.isEmpty()) {
            System.out.println("The string is null or empty");
            return false;
        }
        return true;
    }

    // 1) Reverse string manually using loop — O(n²)
    // Reason: "+" creates a new String each iteration → copies growing prefix every time
    public void reverseManual(String str) {
        System.out.println("Method: reverseManual");
        if (isNotEmpty(str)) {
            System.out.println("String before reverse: " + str);
            int strLength = str.length() - 1;
            String res = "";
            for (int i = strLength; i >= 0; i--) {
                res = res + str.charAt(i);
            }
            System.out.println("String after reverse: " + res);
        }
    }

    // 2) Reverse string using StringBuilder — O(n)
    public void reverseWithBuilder(String str) {
        System.out.println("Method: reverseWithBuilder");
        if (isNotEmpty(str)) {
            System.out.println("String before reverse: " + str);
            // StringBuilder reverses in-place, no new strings created
            StringBuilder builder = new StringBuilder(str);
            builder.reverse(); // single pass O(n)
            String res = builder.toString();
            System.out.println("String after reverse: " + res);
        }
    }

    // 3) Reverse words manually using concatenation, O(n²)
    public void reverseWordsManual(String str) {
        System.out.println("Method: reverseWordsManual");
        if (isNotEmpty(str)) {
            System.out.println("String before reverse: " + str);
            String[] arr = str.split(" ");
            int arrLength = arr.length - 1;
            System.out.println("Reverse: split sentence by spaces, reverse array");
            String res = "";
            for (int i = arrLength; i >= 0; i--) {
                res = res + " " + arr[i];
            }
            res = res.trim();
            System.out.println("String after reverse: " + res);
        }
    }

    // 4) Reverse words using StringBuilder, O(n)
    // one final String is created from the builder
    public void reverseWordsStrBuilder(String str) {
        System.out.println("Method: reverseWordsStrBuilder");
        if (isNotEmpty(str)) {
            System.out.println("String before reverse: " + str);
            String[] arr = str.split(" ");
            int arrLength = arr.length - 1;
            System.out.println("Reverse: with StringBuilder");
            StringBuilder res = new StringBuilder();
            for (int i = arrLength; i >= 0; i--) {
                res.append(arr[i]).append(" ");
            }
            System.out.println("String after reverse with StringBuilder: "
                    + res.toString().trim());
        }
    }

    // 5) Reverse words using StringJoiner, O(n)
    public void reverseWordsJoiner(String str) {
        System.out.println("Method: reverseWordsJoiner");
        if (isNotEmpty(str)) {
            System.out.println("String before reverse: " + str);
            String[] arr = str.split(" ");
            int arrLength = arr.length - 1;
            System.out.println("Reverse: joiner with space");
            StringJoiner res = new StringJoiner(" ");
            for (int i = arrLength; i >= 0; i--) {
                res.add(arr[i]);
            }
            System.out.println("String after reverse with joiner: " + res);
        }
    }

    // 6) Palindrome check (two-pointer technique) — O(n)
    public void isPalindrome(String str) {
        System.out.println("Method: palindromeCheck");
        if (isNotEmpty(str)) {
            System.out.println("Test palindrome for the string: " + str);
            int right = str.length() - 1;
            int left = 0;
            boolean res = true;
            while (left < right && res) {
                if (str.charAt(right) != str.charAt(left)) {
                    res = false;
                }
                right--;
                left++;
            }
            System.out.println("Is string palindrome: " + res);
        }
    }

    // 7) Time: O(n) — single pass; Space: O(1) for ASCII (boolean[256])
    public void removeDuplicateChars(String str) {
        System.out.println("Method: removeDuplicateChars");
        if (isNotEmpty(str)) {
            System.out.println("Before: " + str);
            // create lookup table for all 256 ASCII characters
            // by default, all boolean array elements are false
            // (Java auto-initializes to false)
            boolean[] seenChars = new boolean[256];
            StringBuilder res = new StringBuilder();
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char c = str.charAt(i);
                if (!seenChars[c]) {
                    seenChars[c] = true;
                    res.append(c);
                }
            }
            System.out.println("After: " + res); // Output: "ban"
        }
    }

    // 8) Collapse adjacent duplicates — O(n)
    public void collapseAdjacentRuns(String str) {
        System.out.println("Method: collapseAdjacentRuns");
        if (isNotEmpty(str)) {
            System.out.println("Before: " + str);
            StringBuilder res = new StringBuilder();
            res.append(str.charAt(0)); // always add first symbol
            int length = str.length();
            for (int i = 1; i < length; i++) {
                char curr = str.charAt(i);
                if (curr != str.charAt(i - 1)) {
                    res.append(curr);
                }
            }
            System.out.println("After: " + res); // Output: "abcda"
        }
    }

    // 9) Naive substring search — all matches, including overlapping
    // O(n*m) Note: overlapping matches may cause extra m*k comparisons
    public void indexOfNaiveAll(String str, String pattern) {
        if (isNotEmpty(str) && isNotEmpty(pattern)) {
            int strLength = str.length(); // n
            int patternLength = pattern.length(); // m
            boolean found = false;
            if (patternLength > strLength) {
                System.out.println("Pattern longer than text");
            } else {
                System.out.println("String: " + str
                        + " pattern: " + pattern);
                for (int i = 0; i <= strLength - patternLength; i++) {
                    int j = 0;
                    while (j < patternLength && str.charAt(i + j) == pattern.charAt(j)) {
                        j++;
                    }
                    if (j == patternLength) {
                        System.out.println("Match found at position: " + i);
                        found = true;
                    }
                    // i = i + j; // skip matched letters, if j > 0
                    // skip checked section — avoids overlapping matches
                    // but it can skip matches, like str: "aaaaa", pattern "aaa"
                }
            }
            System.out.println("Match found: " + found);
        }
    }
}
