package com.company.task15;

import java.util.logging.Logger;
import java.util.stream.Stream;


/**
 * 15. Write a call to reduce that can be used to compute the average of a Stream<Double>.
 * Why can’t you simply compute the sum and divide by count()?
 */

public class Main {
    private static final Logger log = Logger.getLogger("Main.class");

    public static void main(String[] args) {
        log.info(String.valueOf(computeAverage()));
    }

    private static double computeAverage() {
        return getDoubleStream()
                .parallel()
                .reduce(new Averager(),Averager::accept, Averager::combine)
                .average();
    }

    private static Stream<Double> getDoubleStream() {
        return Stream.of(12.4,12.5,11.4,90.99,87.1);
    }
}
