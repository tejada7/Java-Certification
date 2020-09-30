package com.javacertification.interviewquestions;

import java.util.*;

/**
 * Contains a bunch of util methods to be used in implementations of {@link List}.
 */
public class ListUtils {

    private ListUtils() {
        throw new IllegalStateException("Constructor not allowed for a util class.");
    }

    /**
     * Remove duplicated elements from a list.
     *
     * @param list the list containing all the elements
     */
    public static <E> List<E> removeDuplicates(List<E> list) {
        if (list != null) {
            Set<E> result = new LinkedHashSet<>(list);
            return new ArrayList<>(result);
        }
        return Collections.emptyList();
    }
}
