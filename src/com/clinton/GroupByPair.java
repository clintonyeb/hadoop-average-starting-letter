package com.clinton;

import java.util.ArrayList;
import java.util.List;

public class GroupByPair<K extends Comparable<? super K>, V> {
    private final K key;
    private List<V> values;

    public GroupByPair(K key) {
        this.key = key;
        this.values = new ArrayList<>();
    }

    public void addValue(V value) {
        this.values.add(value);
    }

    public K getKey() {
        return key;
    }

    public List<V> getValues() {
        return values;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", key, values);
    }
}