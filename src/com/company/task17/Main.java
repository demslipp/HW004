package com.company.task17;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 15. Find the 500 longest strings in War and Peace with a parallel stream.
 * Is it any faster than using a serial stream?
 */

public class Main {
    private static final Logger log = Logger.getLogger("Main.class");
    private static final TextReader textReader = TextReader.getInstance();


    public static void main(String[] args) {
        System.out.println("Parallel: " + get500WordsParallelStream());
        System.out.println("Serial: " + get500WordsSerialStream());
    }


    private static long get500WordsSerialStream() {
        long current = System.currentTimeMillis();

        Map<Integer, List<String>> stringLengthMap = textReader.getWords().stream()
                .collect(Collectors.toMap(String::length,
                        List::of,
                        (v1, v2) -> Stream.concat(v1.stream(), v2.stream()).collect(Collectors.toList())));

        List<String> words = new ArrayList<>();

        List<Integer> keys = new ArrayList<>(stringLengthMap.keySet());
        keys.sort(Comparator.reverseOrder());
        for (Integer key: keys) {
            if (words.size() <= 500) {
                words.addAll(stringLengthMap.get(key));
            }
        }

        words.stream().sorted(Comparator.reverseOrder()).limit(500).collect(Collectors.toList());
        return System.currentTimeMillis() - current;
    }


    private static long get500WordsParallelStream() {
        long current = System.currentTimeMillis();

        Map<Integer, List<String>> stringLengthMap = textReader.getWords().parallelStream()
                .collect(Collectors.toMap(String::length,
                        List::of,
                        (v1, v2) -> Stream.concat(v1.stream(), v2.stream()).collect(Collectors.toList())));

        List<String> words = new ArrayList<>();

        List<Integer> keys = new ArrayList<>(stringLengthMap.keySet());
        keys.sort(Comparator.reverseOrder());
        for (Integer key: keys) {
            if (words.size() <= 500) {
                words.addAll(stringLengthMap.get(key));
            }
        }

        words.parallelStream().sorted(Comparator.reverseOrder()).limit(500).collect(Collectors.toList());
        return System.currentTimeMillis() - current;
    }

}
