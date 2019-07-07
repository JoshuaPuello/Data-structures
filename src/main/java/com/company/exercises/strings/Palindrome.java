package com.company.exercises.strings;

import java.util.Arrays;
import java.util.List;

/**
 * Write a function that takes in a non-empty string and that returns a boolean representing whether or not
 * the string is a palindrome. A palindrome is defined as a string that is written the same forward and backward.
 *
 * Sample input: "abcdcba"
 * Sample output: True (it is a palindrome)
 *
 */
public class Palindrome {

    public static void main(String[] args) {

        for (Object[] input : getInputs()) {

        }

    }

    private static List<Object[]> getInputs() {
        Object[] test1 = new Object[] { "Test 1", "a", true };
        Object[] test2 = new Object[] { "Test 2", "ab", false };
        Object[] test3 = new Object[] { "Test 3", "aba", true };
        Object[] test4 = new Object[] { "Test 4", "abb", false };
        Object[] test5 = new Object[] { "Test 5", "abba", true };
        Object[] test6 = new Object[] { "Test 6", "abcdcba", true };
        Object[] test7 = new Object[] { "Test 7", "abcdefghhgfedcba", true };
        Object[] test8 = new Object[] { "Test 8", "abcdefghihgfedcba", true };
        Object[] test9 = new Object[] { "Test 9", "abcdefghihgfeddcba", false };
        return Arrays.asList(test1, test2, test3, test4, test5, test6, test7, test8, test9);
    }

    /**
     * Time complexity O(n)
     * Space complexity O(1)
     */
    private static boolean isPalindrome(String str) {

        char[] characters = str.toCharArray();
        int left = 0;
        int right = characters.length - 1;

        while (left < right) {
            if (characters[left] != characters[right]) return false;
            left++;
            right--;
        }

        return true;
    }

//    if (str.length() == 1) return true;
//
//    char[] characters = str.toCharArray();
//    Stack<Character> charStack = new Stack<>();
//    Stack<Character> charQueue = new Stack<>();
//
//    int arrayLength = characters.length;
//    int middle = (arrayLength / 2);
//
//    for (int i = 0; i < middle; i++) {
//        charQueue.push(characters[i]);
//        charStack.push(characters[(arrayLength - 1) - i]);
//    }
//
//    while (!charStack.isEmpty()) {
//        if (charStack.pop() != charQueue.pop()) return false;
//    }
//
//    return true;

}
