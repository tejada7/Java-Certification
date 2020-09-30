package com.javacertification.interviewquestions;

import org.junit.Test;

import java.util.Collection;

import static junit.framework.TestCase.assertTrue;

public class DNAUtilsTest {


    @Test
    public void noRepeatedSequences() {
        String dna = "CTAATTCGACTATAGCGCTA";
        Collection<String> result = DNAUtils.repeatedDna(dna);
        assertTrue(result.isEmpty());
    }

    @Test
    public void singleRepeatedSequence() {
        String dna = "CTAATTCGACTATAGCGCTACTAATTCGAC";
        Collection<String> result = DNAUtils.repeatedDna(dna);
        assertTrue(result.contains("CTAATTCGAC"));
    }

    @Test
    public void twoRepeatedSequences() {
        String dna = "CTAATTCGACTATAGCGCTACTAATTCGACGTATAGCGCTA";
        Collection<String> result = DNAUtils.repeatedDna(dna);
        assertTrue(result.contains("CTAATTCGAC"));
        assertTrue(result.contains("TATAGCGCTA"));
    }

    @Test
    public void manyRepeatedOverlappingSequences() {
        String dna =
                "TATAGCGCTAATTCGACGACTATAGCGCTACGACGACTATCTAATTCGAC";
        Collection<String> result = DNAUtils.repeatedDna(dna);
        assertTrue(result.contains("TATAGCGCTA"));
        assertTrue(result.contains("CTAATTCGAC"));
        assertTrue(result.contains("CGACGACTAT"));
    }
}
