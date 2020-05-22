package com.clinton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper {
    private List<Pair<Character, Pair<Integer, Integer>>> pairs;
    private String fileName;
    private final Map<Character, Pair<Integer, Integer>> cache;


    public Mapper(String fileName) {
        pairs = new ArrayList<>();
        this.fileName = fileName;
        this.cache = new HashMap<>();
    }

    public void map() throws IOException {
        List<String> texts = Util.getWordsFromFile(fileName);
        for (String word : texts) {
            char c = word.charAt(0);
            Pair<Integer, Integer> pair = cache.getOrDefault(c, new Pair<>(0, 0));
            pair = addPairs(pair, new Pair<>(word.length(), 1));
            cache.put(c, pair);
        }
        createPairs();
    }

    private void createPairs() {
        for (Character key : cache.keySet()) {
            pairs.add(new Pair<>(key, cache.get(key)));
        }
//        pairs.sort(Comparator.comparing(p -> p.key));
    }

    private Pair<Integer, Integer> addPairs(Pair<Integer, Integer> pair1, Pair<Integer, Integer> pair2) {
        return new Pair<>(pair1.key + pair2.key, pair1.value + pair2.value);
    }


    public List<Pair<Character, Pair<Integer, Integer>>> getPairs() {
        return pairs;
    }
}
