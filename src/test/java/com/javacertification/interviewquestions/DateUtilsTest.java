package com.javacertification.interviewquestions;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.BDDAssertions.then;

class DateUtilsTest {

    @ParameterizedTest
    @CsvSource(textBlock = """
        1992, true
        2001, false
        1996, true
        2005, false
        """)
    void isLeapYearTest(int year, boolean expected) {
        then(DateUtils.isLeapYear(year)).isEqualTo(expected);
    }
}
