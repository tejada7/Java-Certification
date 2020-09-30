package com.javacertification.interviewquestions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Logger;

/**
 * Compare two version numbers, for example, give two versions 2.2.5 and 2.3 -> 2.3 is greater and 2.2.5.
 */
public class VersionNumberComparator implements Comparator<String> {

    private static Logger logger = Logger.getLogger(VersionNumberComparator.class.getName());

    /**
     * {@inheritDoc}
     *
     * @param v1 the first version parameter
     * @param v2 the first version parameter
     * @return 0 if v1 and v2 are equals, 1 if v1 is greater than v2, -1 otherwise
     */
    @Override
    public int compare(String v1, String v2) {
        if (null == v1 || null == v2) {
            throw new IllegalArgumentException("No null arguments are allowed");
        }
        Integer[] array1 = Arrays.stream(v1.split("\\.")).map(Integer::parseInt).toArray(Integer[]::new);
        Integer[] array2 = Arrays.stream(v2.split("\\.")).map(Integer::parseInt).toArray(Integer[]::new);

        int length1 = array1.length;
        int length2 = array2.length;

        int max = Math.max(length1, length2);

        for (int i = 0; i < max; i++) {
            try {
                int result = Integer.compare(array1[i], array2[i]);
                if (0 != result) {
                    return result;
                }
            } catch (IndexOutOfBoundsException iobe) {
                logger.warning(iobe.getMessage());
            }
        }
        return 0;
    }
}
