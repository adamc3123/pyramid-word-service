package com.pyramid;

import java.util.HashMap;
import java.util.Map;

/**
 * A pyramid word must have characters of increasing frequency with no
 * gaps and no duplications. This means pyramid words have restricted character
 * lengths. For example, a length of 3 is valid because ann is a valid pyramid
 * word, however a length of 4 is not valid because the frequency of letters
 * will have duplicates.
 */
public class Pyramid {

    /**
     *
     * @param testStr
     * @return
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
     * @param strLen
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

