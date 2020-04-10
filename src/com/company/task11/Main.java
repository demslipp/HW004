package com.company.task11;

import com.company.task9.TextReader;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * 11. Given a finite stream of strings, find all strings of maximum length.
 */
public class Main {

    private static final Logger log = Logger.getLogger("Main.class");

    public static void main(String[] args) {
        log.info(getMaxLenStrings().toString());
    }

    private static List<String> getMaxLenStrings() {
        Map<Integer, List<String>> stringLengthMap = TextReader.getStringsStream()
                .collect(Collectors.toMap(String::length,
                        List::of,
                        (v1, v2) -> Stream.concat(v1.stream(), v2.stream()).collect(Collectors.toList())));

        Integer maxLength = stringLengthMap.keySet()
                .stream()
                .max(Integer::compare)
                .orElseThrow(NullPointerException::new);

        return stringLengthMap.get(maxLength);
    }

}
