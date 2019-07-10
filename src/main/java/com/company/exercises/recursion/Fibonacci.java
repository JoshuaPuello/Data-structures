package com.company.exercises.recursion;

import com.company.utils.Utils;

import java.util.HashMap;

/**
 * The Fibonacci sequence is defined as follows: the first number of the sequence is 0, the second
 * number is 1, and the nth number is the sum of the (n - 1)th and (n - 2)th numbers. Write a function
 * that takes in an integer n and returns the nth Fibonacci number.
 *
 * Sample input: 6
 * Sample output: 5 (0, 1, 1, 2, 3, 5)
 *
 */
public class Fibonacci {

    public static final int TEST_NAME_INDEX = 0;
    public static final int INPUT_INDEX = 1;
    public static final int EXPECTED_VALUE_INDEX = 2;

    public static void main(String[] args) {
        for (Object[] input : getInputs()) {
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    fibonacciIterative((int) input[INPUT_INDEX]),
                    (int) input[EXPECTED_VALUE_INDEX]);
        }

    }

    private static Object[][] getInputs() {
        Object[][] tests = {
                { "Test 1", 1, 0 }, { "Test 2", 2, 1 },
                { "Test 3", 3, 1 }, { "Test 4", 4, 2 },
                { "Test 5", 5, 3 }, { "Test 6", 6, 5 },
                { "Test 7", 7, 8 }, { "Test 8", 8, 13 },
                { "Test 9", 9, 21 }, { "Test 10", 17, 987 },
                { "Test 11", 18, 1597 }
        };
        return tests;
    }

    /**
     * Time complexity is O(2^n) due to the two calls executed in almost every step.
     * Space complexity O(n) due to call stack.
     */
    private static int fibonacciRecursive(int n) {
        if (n == 2) return 1;
        else if (n == 1) return 0;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    /**
     * Time complexity is O(n)
     * Space complexity is O(n)
     */
    private static int fibonacciMemoization(int n) {
        HashMap<Integer, Integer> memoize = new HashMap<>();
        memoize.put(1, 0);
        memoize.put(2, 1);
        return getNthFib(n, memoize);
    }

    private static int getNthFib(int n, HashMap<Integer, Integer> memoize) {
        if (memoize.containsKey(n)) {
            return memoize.get(n);
        } else {
            memoize.put(n, getNthFib(n - 1, memoize) + getNthFib(n - 2, memoize));
            return memoize.get(n);
        }
    }

    /**
     * Time complexity O(n)
     * Space complexity O(1)
     */
    private static int fibonacciIterative(int n) {

        if (n == 1) return 0;
        else if (n == 2) return 1;

        int[] pairPrevValues = { 0, 1 };

        for (int i = 3; i <= n; i++) {
            int newValue = pairPrevValues[0] + pairPrevValues[1];
            pairPrevValues[0] = pairPrevValues[1];
            pairPrevValues[1] = newValue;
        }

        return pairPrevValues[1];
    }
}
