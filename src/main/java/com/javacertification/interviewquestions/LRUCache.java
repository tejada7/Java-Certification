package com.javacertification.interviewquestions;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implementation of a least recently used cache to store the more used elements into a double linked list and map
 * structure.
 *
 * @param <K> the key
 * @param <V> the value
 *
 * @author Favio
 */
public class LRUCache<K, V> {
    private Map<K, V> map;

    /**
     * Initialise the structure based on a given capacity.
     *
     * @param cacheSize the capacity
     */
    public LRUCache(int cacheSize) {
        map = new LinkedHashMap<K, V>(16, .75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > cacheSize;
            }
        };
    }

    /**
     * Retrieve the value given a key.
     *
     * @param key the key of type {@link K}
     *
     * @return the value of type {@link V} if found, null otherwise
     */
    public V get(K key) {
        return map.get(key);
    }

    /**
     * Associate the specified value with the key.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     */
    public void set(K key, V value) {
        map.put(key, value);
    }
}
