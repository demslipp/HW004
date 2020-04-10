package com.company.task9;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
/**
 * 9. Read the words from /usr/share/dict/words (or a similar word list)
 *    into a stream and produce an array of all words containing five distinct vowels.
 */

public class Main {
    private static final Logger log = Logger.getLogger("Main.class");

    private static final TextReader textReader = TextReader.getInstance();

    public static void main(String[] args) {
        log.info(collectWords(textReader.getWords()).toString());
    }

    private static List<String> collectWords(List<String> words) {
        return words.stream().filter(word -> {
            List<Character> vowelEntries = new ArrayList<>();
            Character[] characters = word.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
            for (Character c : characters) {
                if (!vowelEntries.contains(c) && textReader.isVowel(c)) {
                    vowelEntries.add(c);
                }
            }
            return vowelEntries.size() >= 5;
        }).collect(Collectors.toList());
    }


}
