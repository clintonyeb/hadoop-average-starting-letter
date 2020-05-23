package com.clinton;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Util {
    private static final String DELIMITER = "[\\s-]+";

    public static List<String> getWordsFromFile(String fileName) throws IOException {
        List<String> res = new ArrayList<>();
        Files.lines(Path.of(fileName)).forEach(line -> res.addAll(processLine(line)));
        return res;
    }

    public static List<String> processLine(String line) {
        List<String> res = new ArrayList<>();
        for (String word : line.split(DELIMITER)) {
            String format = processWord(word);
            if (format != null) res.add(format);
        }
        return res;
    }

    public static  <T> void printAll(List<T> list) {
        list.forEach(System.out::println);
    }

    public static <K, V> void mergeMap(Map<K, List<V>> end, Map<K, List<V>> from) {
        for (K k : from.keySet()) {
            List<V> values;
            if(end.containsKey(k)) {
                values = end.get(k);
            } else {
                values = new ArrayList<>();
            }
            values.addAll(from.get(k));
            end.put(k, values);
        }
    }

    private static String processWord(String w) {
        String word = w.trim().replaceAll("\"", "").toLowerCase();
        return formatText(word);
    }

    private static String formatText(String text) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (!Character.isLetter(c)) {
                if (i != 0 && i != text.length() - 1) return null;
            } else {
                res.append(c);
            }
        }
        if (res.length() < 1) {
            return null;
        }
        return res.toString();
    }
}
