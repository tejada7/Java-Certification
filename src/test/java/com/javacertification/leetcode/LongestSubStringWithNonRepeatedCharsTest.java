package com.javacertification.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongestSubStringWithNonRepeatedCharsTest {

    LongestSubStringWithNonRepeatedChars l = new LongestSubStringWithNonRepeatedChars();

    @Test
    void maxLengthNonRepeatedWord() {
        Assertions.assertAll(
                ()-> assertEquals(3, l.maxLengthNonRepeatedWord("abcabcbb")),
                ()-> assertEquals(1, l.maxLengthNonRepeatedWord("bbbbb")),
                ()-> assertEquals(3, l.maxLengthNonRepeatedWord("pwwkew")),
                ()-> assertEquals(0, l.maxLengthNonRepeatedWord(null))
        );
    }
}