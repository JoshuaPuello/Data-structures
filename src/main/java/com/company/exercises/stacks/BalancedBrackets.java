package com.company.exercises.stacks;

import com.company.utils.Utils;

import java.util.HashMap;
import java.util.Stack;

/**
 * Write a function that takes in a string made up of brackets ("(", "[", "{", ")", "]", and "}")
 * and other optional characters. The function should return a boolean representing whether or not
 * the string is balanced in regards to brackets. A string is said to be balanced if it has as many
 * opening brackets of a given type as it has closing brackets of that type and if no bracket is
 * unmatched. Note that a closing bracket cannot match a corresponding opening bracket that comes
 * after it. Similarly, brackets cannot overlap each other as in "[(])".
 *
 * Sample input: "([])(){}(())()()"
 * Sample output: True (it is balanced)
 */
public class BalancedBrackets {

    public static final int TEST_NAME_INDEX = 0;
    public static final int INPUT_INDEX = 1;
    public static final int EXPECTED_VALUE_INDEX = 2;

    public static void main(String[] args) {
        for (Object[] input : getInputs()) {
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    balancedBrackets(String.valueOf(input[INPUT_INDEX])),
                    (boolean) input[EXPECTED_VALUE_INDEX]);
        }

    }

    private static Object[][] getInputs() {
        Object[][] tests = {
                { "Test 1", "()[]{}{", false },
                { "Test 2", "(((((({{{{{[[[[[([)])]]]]]}}}}}))))))", false },
                { "Test 3", "()()[{()})]", false },
                { "Test 4", "{}()", true },
                { "Test 5", "([])(){}(())()()", true },
                { "Test 6", "((({})()))", true },
                { "Test 7", "{[[[[({(}))]]]]}", false },
                { "Test 8", "(((((([[[[[[{{{{{{{{{{{{()}}}}}}}}}}}}]]]]]]))))))((([])({})[])[])[]([]){}(())", true },
                { "Test 9", "((){{{{[]}}}})", true }
        };
        return tests;
    }

    /**
     * Time complexity O(n)
     * Space complexity O(n)
     */
    private static boolean balancedBrackets(String str) {
        String openingBrackets = "({[";
        String closingBrackets = ")}]";
        HashMap<Character, Character> matchingBrackets = new HashMap<>();
        matchingBrackets.put(')', '(');
        matchingBrackets.put('}', '{');
        matchingBrackets.put(']', '[');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (openingBrackets.indexOf(c) != -1) {
                stack.push(c);
            } else if (closingBrackets.indexOf(c) != -1) {
                if (stack.isEmpty()) return false;
                if (stack.pop() != matchingBrackets.get(c)) return false;
            }
        }
        return stack.isEmpty();
    }
}
