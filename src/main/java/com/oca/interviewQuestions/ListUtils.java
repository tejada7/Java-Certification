package com.oca.interviewQuestions;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Contains a bunch of util methods to be used in implementations of {@link List}.
 */
public class ListUtils {

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
        return null;
    }
}
