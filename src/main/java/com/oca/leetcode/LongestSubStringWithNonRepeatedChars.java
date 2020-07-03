package com.oca.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 */
public class LongestSubStringWithNonRepeatedChars {
    public int maxLengthNonRepeatedWord(String s) {
        if (null == s || s.isEmpty()) {
            return 0;
        }
        int longestSubString = 0;
        int left = 0;
        int right = 0;
        Set<Character> words = new HashSet<>();
        while (right < s.length()) {
            if (!words.contains(s.charAt(right))) {
                words.add(s.charAt(right));
                longestSubString = Math.max(longestSubString, words.size());
                right++;
            } else {
                words.remove(s.charAt(left));
                left++;
            }
        }
        return longestSubString;
    }
}
