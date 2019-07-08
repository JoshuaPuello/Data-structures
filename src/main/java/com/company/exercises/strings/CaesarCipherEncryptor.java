package com.company.exercises.strings;

import com.company.utils.Utils;

import java.util.HashMap;

/**
 * Given a non-empty string of lowercase letters and a non-negative integer value representing a key, write a
 * function that returns a new string obtained by shifting every letter in the input string by k positions in
 * the alphabet, where k is the key. Note that letters should "wrap" around the alphabet; in other words, the
 * letter "z" shifted by 1 returns the letter "a".
 * <p>
 * Sample input: "xyz", 2
 * Sample output: "zab"
 */
public class CaesarCipherEncryptor {

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    public static final int EXPECTED_VALUE_INDEX = 3;
    public static final int INPUT_INDEX = 1;
    public static final int KEY_INDEX = 2;
    public static final int TEST_NAME_INDEX = 0;

    public static void main(String[] args) {
        for (Object[] input : getInputs()) {
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    encryptorUnicode(String.valueOf(input[INPUT_INDEX]), (int) input[KEY_INDEX]),
                    String.valueOf(input[EXPECTED_VALUE_INDEX]));
        }

    }

    private static Object[][] getInputs() {
        Object[][] tests = {
                { "Test 1", "abc", 0, "abc" },
                { "Test 2", "abc", 3, "def" },
                { "Test 3", "xyz", 2, "zab" },
                { "Test 4", "abc", 52, "abc" }
        };
        return tests;
    }

    /**
     * Time complexity O(n)
     * Space complexity O(n)
     */
    private static String encryptorUnicode(String str, int key) {

        char[] newLetters = new char[str.length()];
        int newKey = key % 26;

        for (int i = 0; i < str.length(); i++) {
            newLetters[i] = getNewLetter(str.charAt(i), newKey);
        }

        return String.valueOf(newLetters);
    }

    private static char getNewLetter(char letter, int key) {
        int newIndex = letter + key;
        return newIndex <= 122 ? (char) newIndex : (char) (96 + newIndex % 122);
    }

    private static String encryptorHashMap(String str, int key) {

        char[] alphabetArray = ALPHABET.toCharArray();
        char[] strInputArray = str.toCharArray();
        int alphabetLength = alphabetArray.length;

        HashMap<Character, Integer> hashMap = new HashMap<>();
        StringBuilder builder = new StringBuilder(str.length());

        for (int i = 0; i < alphabetLength; i++) {
            hashMap.put(alphabetArray[i], i);
        }

        for (char c : strInputArray) {
            int currentIndex = hashMap.get(c);
            int alphabetIndex = (currentIndex + key) % alphabetLength;

            builder.append(alphabetArray[alphabetIndex]);
        }

        return builder.toString();
    }

}
