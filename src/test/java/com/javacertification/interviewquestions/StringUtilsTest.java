package com.javacertification.interviewquestions;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.javacertification.interviewquestions.StringUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDSoftAssertions.thenSoftly;

class StringUtilsTest {

    @Test
    void oneCharacter() {
        assertThat(recursivePermutation("A")).contains("A");
        assertThat(iterativePermutation("A")).contains("A");
    }

    @Test
    void twoCharacters() {
        List<String> result = recursivePermutation("AB");
        assertThat(result).contains("AB");
        assertThat(result).contains("BA");

        List<String> result1 = iterativePermutation("AB");
        assertThat(result1).contains("AB");
        assertThat(result1).contains("BA");
    }

    @Test
    void threeCharacters() {
        List<String> result = recursivePermutation("ABC");
        thenSoftly(softly -> {
            softly.then(result).contains("ACB");
            softly.then(result).contains("ABC");
            softly.then(result).contains("CAB");
            softly.then(result).contains("BCA");
            softly.then(result).contains("CBA");
            softly.then(result).contains("BAC");
        });

        List<String> result1 = iterativePermutation("ABC");
        thenSoftly(softly -> {
            softly.then(result1).contains("ACB");
            softly.then(result1).contains("ABC");
            softly.then(result1).contains("CAB");
            softly.then(result1).contains("BCA");
            softly.then(result1).contains("CBA");
            softly.then(result1).contains("BAC");
        });
    }

    @Test
    void isResultSizeFactorial() {
        List<String> result = recursivePermutation("12345678");
        assertThat(result).hasSize(1 * 2 * 3 * 4 * 5 * 6 * 7 * 8);

        List<String> result1 = iterativePermutation("12345678");
        assertThat(result1).hasSize(1 * 2 * 3 * 4 * 5 * 6 * 7 * 8);
    }

    @Test
    void isStringBalancedWithParenthesisAndBrackets() {
        thenSoftly(softly -> {
            softly.then(isBalanced("[()]")).isTrue();
            softly.then(isBalanced("(()[])")).isTrue();
            softly.then(isBalanced("([)]")).isFalse();
            softly.then(isBalanced("((")).isFalse();
            softly.then(isBalanced("[(()])")).isFalse();
            softly.then(isBalanced("([(([[(([]))]]))])")).isTrue();
            softly.then(isBalanced("[](()()[[]])()[]([])")).isTrue();
            softly.then(isBalanced("([((([(([]))])))))])")).isFalse();
            softly.then(isBalanced("[](()()[[]])[][[([])")).isFalse();
        });
    }
}
