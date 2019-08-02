package com.company.exercises.recursion;

import com.company.utils.Utils;

/**
 * Write a function that takes in a non-negative integer n and that returns the number
 * of possible Binary Tree topologies that can be created with exactly n nodes. A Binary
 * Tree topology is defined as any Binary Tree configuration, irrespective of node values.
 * For instance, there exist only two Binary Tree topologies when n is equal to 2: a root
 * node with a left node, and a root node with a right node. Node that when n is equal
 * to 0, there is one topology that can be created: the None (null) node.
 * ​
 * Sample input: 3
 * Sample output: 5
 */
public class BinaryTreeTopologies {

    public static final int TEST_NAME_INDEX = 0;
    public static final int INPUT_INDEX = 1;
    public static final int EXPECTED_VALUE_INDEX = 2;

    public static void main(String[] args) {
        for (Object[] input : getInputs()) {
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    numberOfBinaryTreeTopologies((int) input[INPUT_INDEX]),
                    (int) input[EXPECTED_VALUE_INDEX]);
        }
    }

    private static Object[][] getInputs() {
        Object[][] tests = {
                {"Test 1", 0, 1},
                {"Test 2", 1, 1},
                {"Test 3", 2, 2},
                {"Test 4", 3, 5},
                {"Test 5", 4, 14},
                {"Test 6", 5, 42},
                {"Test 7", 6, 132},
                {"Test 8", 7, 429},
        };
        return tests;
    }

    /**
     * Upper Bound: O((n*(2n)!)/(n!(n+1)!)) time
     * Space complexity: O(n) space
     */
    private static int numberOfBinaryTreeTopologies(int n) {

        if (n == 0) {
            return 1;
        }

        int numberOfTrees = 0;

        for (int leftTreeSize = 0; leftTreeSize < n; leftTreeSize++) {
            int rightTreeSize = n - 1 - leftTreeSize;
            int numberOfLeftTrees = numberOfBinaryTreeTopologies(leftTreeSize);
            int numberOfRightTrees = numberOfBinaryTreeTopologies(rightTreeSize);
            numberOfTrees += numberOfLeftTrees * numberOfRightTrees;
        }

        return numberOfTrees;

    }

}
