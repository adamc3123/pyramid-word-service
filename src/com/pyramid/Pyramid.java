package com.pyramid;

import java.util.HashMap;
import java.util.Map;

/**
 * This class checks if a string is a pyramid word.
 *
 * A word is a pyramid word if you can arrange the letters in increasing frequency
 * starting from 1 and continuing without gaps and without duplicates
 *
 * Note: In this implementation, spaces and special characters can be used to create
 * valid pyramid words
 */
public class Pyramid {

    /**
     * This method checks if the provided string is a pyramid word.
     *
     * First, the length of the string is verified to ensure a pyramid word
     * is possible. Then, each letter in the string is placed into a hash table
     * where the letter is the key and and the frequency of the letter is the
     * value. Finally, the keys in the hash table are placed into an array using the
     * key's value as the index. If a key is greater than the size of the array or
     * two keys map to the same index, the string is not a pyramid word.
     *
     * @param testStr string to check
     * @return true if testStr is a pyramid word, otherwise false
     */
    public static Boolean solvePyramid(String testStr) {
        int numUniqueLets = 0;
        int numLetsPlaced = 0;
        HashMap<Character, Integer> letterFreq = new HashMap<Character, Integer>();

        testStr = testStr.toLowerCase();

        // Check if a pyramid word is possible given the string length
        try {
            numUniqueLets = checkPyramidLen(testStr.length());
        } catch (PyramidStrLenException msg) {
            // testStr cannot be a pyramid word
            return false;
        }

        for(Character chr : testStr.toCharArray()) {
            Integer freq = letterFreq.get(chr);
            if (freq != null)
                freq++;
            else
                freq = 1;
            letterFreq.put(chr, freq);
        }

        Character[] letters = new Character[numUniqueLets];
        for (Map.Entry<Character, Integer> letter : letterFreq.entrySet()) {
            if (letter.getValue() > numUniqueLets){
                // This letter occurred too many times
                return false;
            }
            if (letters[letter.getValue() - 1] != null) {
                // Cannot have duplicates # of letters in the word
                return false;
            }
            letters[letter.getValue() - 1] = letter.getKey();
            numLetsPlaced++;
        }

        if (numLetsPlaced == numUniqueLets) {
            return true;
        }



        return false;
    }

    /**
     * Length is verified using: strLen = n(n+1)/2 (solve for n)
     * Where n must be a whole number representing the number of different
     * letters possible in a pyramid word of length strLen.
     *
     * Since pyramid words must have characters of increasing frequency with no
     * gaps and no duplications there is a restriction on string length. For example,
     * a length of 3 is valid because 1 char + 2 chars is a valid pyramid word, however
     * a length of 4 is not valid because the frequency of letters will have duplicates.
     * This quick check saves times when a provided string is not the correct pyramid word
     * length.
     *
     * @param strLen length of the word being checked
     * @return the number of distinct letters that can be present in the string
     * for the string to be a pyramid word
     */
    public static int checkPyramidLen(int strLen) throws PyramidStrLenException {
        double sqrtTerm;
        double finalTerm;

        sqrtTerm = Math.sqrt(1 + (8 * strLen));
        finalTerm = (-1 + sqrtTerm) / 2;

        if(finalTerm == (int)(finalTerm)) {
           return (int)finalTerm;
        } else {
            throw new PyramidStrLenException();
        }
    }
}

