package com.javacertification.interviewquestions;

import org.json.JSONObject;

import java.util.function.BiFunction;

public class RecursiveJsonFinder implements BiFunction<String, String, Object> {

    @Override
    public Object apply(final String input, final String pathToFind) {
        final var json = new JSONObject(input);
        final var paths = pathToFind.split("\\.");
        return findPathInJson(json, paths, 0);
    }

    private Object findPathInJson(final JSONObject json, final String[] paths, int index) {
        if (isLastIndex(paths, index) && json.get(paths[index]) != null) {
            return json.get(paths[index]);
        }
        return findPathInJson(json.getJSONObject(paths[index]), paths, ++index);
    }

    private boolean isLastIndex(final String[] paths, final int index) {
        return paths.length - 1 == index;
    }
}
