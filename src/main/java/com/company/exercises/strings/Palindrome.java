package com.company.exercises.strings;

import com.company.utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Write a function that takes in a non-empty string and that returns a boolean representing whether or not
 * the string is a palindrome. A palindrome is defined as a string that is written the same forward and backward.
 *
 * Sample input: "abcdcba"
 * Sample output: True (it is a palindrome)
 *
 */
public class Palindrome {

    public static final int TEST_NAME_INDEX = 0;
    public static final int INPUT_INDEX = 1;
    public static final int EXPECTED_VALUE_INDEX = 2;

    public static void main(String[] args) {

        for (Object[] input : getInputs()) {
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    isPalindromeIterative(String.valueOf(input[INPUT_INDEX])),
                    (boolean) input[EXPECTED_VALUE_INDEX]);
        }

    }

    private static Object[][] getInputs() {
        Object[][] tests = {
                { "Test 1", "a", true },
                { "Test 2", "ab", false },
                { "Test 3", "aba", true },
                { "Test 4", "abb", false },
                { "Test 5", "abba", true },
                { "Test 6", "abcdcba", true },
                { "Test 7", "abcdefghhgfedcba", true },
                { "Test 8", "abcdefghihgfedcba", true },
                { "Test 9", "abcdefghihgfeddcba", false }
        };
        return tests;
    }

    /**
     * Time complexity O(n)
     * Space complexity O(1)
     */
    private static boolean isPalindromeIterative(String str) {

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

    private static boolean isPalindromeStack(String str) {

        int arrayLength = str.length();
        if (arrayLength == 1) return true;

        int middle = (arrayLength / 2);
        char[] characters = str.toCharArray();
        Stack<Character> leftStack = new Stack<>();
        Stack<Character> rightStack = new Stack<>();

        for (int i = 0; i < middle; i++) {
            rightStack.push(characters[i]);
            leftStack.push(characters[(arrayLength - 1) - i]);
        }

        while (!leftStack.isEmpty()) {
            if (leftStack.pop() != rightStack.pop()) return false;
        }

        return true;
    }

    private static boolean isPalindromeStrBuilder(String str) {

        char[] characters = str.toCharArray();
        StringBuilder builder = new StringBuilder(str.length());

        for (int i = characters.length - 1; i >= 0; i--) {
            builder.append(characters[i]);
        }

        return builder.toString().equals(str);
    }

}
