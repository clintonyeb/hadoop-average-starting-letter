package com.clinton;

public class Pair<K extends Comparable<? super K>, V> {
    K key;
    V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", key, value);
    }
}
