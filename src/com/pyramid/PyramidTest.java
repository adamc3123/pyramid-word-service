package com.pyramid;

import java.util.ArrayList;

/**
 * Pyramid implementation testing class
 */
public class PyramidTest {

    /**
     * Creates a list of strings that are not pyramid words
     * @return list of words that will return false
     */
    public static ArrayList<String> falseTests() {
        ArrayList<String> set = new ArrayList<String>();
        set.add("bandana");
        set.add("aa");
        set.add("aaadfavadfavdavdadvasdvkanvlakdv");
        set.add("heelllooo");
        set.add("11112222aaabbc");
        set.add("1   222");

        return set;
    }

    /**
     * Creates a list of strings that are pyramid words
     * @return list of words that will return true
     */
    public static ArrayList<String> trueTests(){
        ArrayList<String> set = new ArrayList<String>();
        set.add("a");
        set.add("1");
        set.add("banana");
        set.add("322133");
        set.add("   112");
        set.add("@@@!!#");
        set.add("1223334444");
        set.add("nan");
        set.add("abbcccddddeeeee");
        set.add("aaaaaabbbbbccccdddeef");
        set.add("aacaabaabbfbcdccbdeed");
        set.add("11c11b11bbfbcdccbdeed");

        return set;
    }

    /**
     * Runs the pyramid word checker on a list of strings and checks for expected result
     * @param testVals a list of words to check
     * @param expected expected result
     */
    public static void runPyramidTest(ArrayList<String> testVals, Boolean expected){
        for (String test : testVals) {
            if(Pyramid.solvePyramid(test) != expected) {
                System.out.println("FAIL: \"" + test + "\" returned " + !expected);
            }
        }
    }

    /**
     * Tests the pyramid word length checker. Verifies that pyramid word lengths
     * return the correct length and other lengths cause an exception.
     */
    public static void runPyramidLenTest() {
        ArrayList<Integer> correctVals =  new ArrayList<Integer>();
        int sumIx = 0;
        int numlets = 0;

        // Check valid pyramid lengths
        for (int ix = 1; ix < 500; ix++) {
            sumIx += ix;
            correctVals.add(sumIx);
            try {
                numlets = Pyramid.checkPyramidLen(sumIx);
                if (numlets != ix) {
                    System.out.println("FAIL: string length of " + sumIx + " returned " +
                            numlets + " distinct letters.");
                }
            } catch (PyramidStrLenException e) {
                System.out.println("FAIL: string length of " + sumIx + " caused an exception");
            }
        }

        // Check incorrect pyramid lengths
        for(int ix = 1; ix < 10000; ix++) {
            // Skip good lengths
            if (correctVals.get(0) == ix) {
                correctVals.remove(0);
                continue;
            }
            try {
                numlets = Pyramid.checkPyramidLen(ix);
                System.out.println("FAIL: string length of " + ix + " did not cause an exception." +
                        "Returned " + numlets + " distinct letters.");
            } catch (PyramidStrLenException e) {
                // PASS; do nothing
            }
        }
    }

    /**
     * Tests the pyramid word checker implementation
     * @param args none
     */
    public static void main(String[] args) {
        System.out.println("INFO: Starting tests");
        runPyramidTest(trueTests(), true);
        runPyramidTest(falseTests(), false);
        runPyramidLenTest();
        System.out.println("INFO: Testing complete");

    }
}
