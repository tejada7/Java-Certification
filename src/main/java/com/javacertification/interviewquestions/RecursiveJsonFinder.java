package com.javacertification.interviewquestions;

import org.json.JSONObject;

import java.util.HashSet;
import java.util.Optional;
import java.util.function.BiFunction;

public final class RecursiveJsonFinder implements BiFunction<String, String, Optional<Object>> {

    @Override
    public Optional<Object> apply(final String input, final String pathToFind) {
        final var json = new JSONObject(input);
        final var paths = pathToFind.split("\\.");
        return findPathInJson(json, paths, 0);
    }

    private Optional<Object> findPathInJson(final JSONObject json, final String[] paths, final int index) {
        final var node = paths[index];
        if (doesJsonContainNode(json, node)) {
            return switch (json.get(node)) {
                case JSONObject j -> !isLastIndex(paths, index) && doesJsonContainNode(j, paths[index + 1])
                        ? findPathInJson(j, paths, index + 1)
                        : Optional.of(j.toString());
                case Object o -> Optional.of(o);
            };
        }
        return Optional.empty();
    }

    private boolean isLastIndex(final String[] paths, final int index) {
        return paths.length - 1 == index;
    }

    private static boolean doesJsonContainNode(final JSONObject json, final String path) {
        return new HashSet<>(json.names().toList()).contains(path);
    }
}
