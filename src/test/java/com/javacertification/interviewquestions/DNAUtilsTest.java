package com.javacertification.interviewquestions;

import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDSoftAssertions.thenSoftly;

class DNAUtilsTest {

    @Test
    void noRepeatedSequences() {
        // Given
        String dna = "CTAATTCGACTATAGCGCTA";

        // When
        Collection<String> result = DNAUtils.repeatedDna(dna);

        // Then
        then(result).isEmpty();
    }

    @Test
    void singleRepeatedSequence() {
        // Given
        String dna = "CTAATTCGACTATAGCGCTACTAATTCGAC";

        // When
        Collection<String> result = DNAUtils.repeatedDna(dna);

        // Then
        then(result).contains("CTAATTCGAC");
    }

    @Test
    void twoRepeatedSequences() {
        // Given
        String dna = "CTAATTCGACTATAGCGCTACTAATTCGACGTATAGCGCTA";

        // When
        Collection<String> result = DNAUtils.repeatedDna(dna);

        // Then
        thenSoftly(softly -> {
            softly.then(result).contains("CTAATTCGAC");
            softly.then(result).contains("TATAGCGCTA");
        });
    }

    @Test
    void manyRepeatedOverlappingSequences() {
        // Given
        String dna = "TATAGCGCTAATTCGACGACTATAGCGCTACGACGACTATCTAATTCGAC";

        // When
        Collection<String> result = DNAUtils.repeatedDna(dna);

        // Then
        thenSoftly(softly -> {
            softly.then(result).contains("TATAGCGCTA");
            softly.then(result).contains("CTAATTCGAC");
            softly.then(result).contains("CGACGACTAT");
        });
    }
}
