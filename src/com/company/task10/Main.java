package com.company.task10;


import com.company.task9.TextReader;

import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * 10. Given a finite stream of strings, find the average string length.
 */

public class Main {
    private static final Logger log = Logger.getLogger("Main.class");

    public static void main(String[] args) {
        log.info(String.valueOf(getAverageLen()));
    }

    private static int getAverageLen() {
        return TextReader.getStringsStream()
                .collect(Collectors.averagingInt(String::length))
                .intValue();
    }


}
