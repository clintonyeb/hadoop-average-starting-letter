package com.clinton;

import java.util.*;

public class Reducer {
    private final List<Pair<Character, Pair<Integer, Integer>>> pairs;
    private List<GroupByPair<Character, Pair<Integer, Integer>>> groupedPairs;
    private List<Pair<Character, Double>> averagePairs;

    public Reducer(List<Pair<Character, Pair<Integer, Integer>>> pairs) {
        this.pairs = pairs;
    }

    public void reduce() {
        groupedPairs = groupPairs();
        averagePairs = averageGroupPairs();
    }

    private List<GroupByPair<Character, Pair<Integer, Integer>>> groupPairs() {
        Map<Character, GroupByPair<Character, Pair<Integer, Integer>>> cache = new HashMap<>();
        for (Pair<Character, Pair<Integer, Integer>> pair : pairs) {
            GroupByPair<Character, Pair<Integer, Integer>> current;
            if (cache.containsKey(pair.key)) {
                current = cache.get(pair.key);
            } else {
                current = new GroupByPair<>(pair.key);
            }
            current.addValue(pair.value);
            cache.put(pair.key, current);
        }
        List<GroupByPair<Character, Pair<Integer, Integer>>> groups = new ArrayList<>();
        for (Character key : cache.keySet()) {
            groups.add(cache.get(key));
        }
        Collections.sort(groups);
        return groups;
    }

    private List<Pair<Character, Double>> averageGroupPairs() {
        List<Pair<Character, Double>> pairs = new ArrayList<>();
        for (GroupByPair<Character, Pair<Integer, Integer>> groupPair : groupedPairs) {
            GroupByPair<Character, Integer> group = new GroupByPair<>(groupPair.getKey());

            int sum = 0;
            int count = 0;
            for (Pair<Integer, Integer> averagePair : groupPair.getValues()) {
                sum += averagePair.key;
                count += averagePair.value;
            }

            double average = 0;
            if(count != 0) average = sum / (double) count;

            pairs.add(new Pair<>(group.getKey(), average));
        }
        Collections.sort(pairs);
        return pairs;
    }

    public List<GroupByPair<Character, Pair<Integer, Integer>>> getGroupedPairs() {
        return groupedPairs;
    }

    public List<Pair<Character, Double>> getAveragePairs() {
        return averagePairs;
    }
}
