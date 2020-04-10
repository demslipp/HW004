package com.company.task17;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Stream;


public class TextReader {


    private final Logger log = Logger.getLogger("TextReader.class");

    private static TextReader instance;

    private final String textSource = String.format("src/%s/PAW.txt",
            TextReader.class
                    .getPackage()
                    .getName().replace(".","/"));

    private final List<String> words = new ArrayList<>();

    private TextReader() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(textSource));
        } catch (FileNotFoundException e) {
            log.warning("File does not exist.");
            System.exit(1);
        }

        while(scanner.hasNext()) {
            words.addAll(Arrays.asList(scanner.nextLine().split(" ")));
        }
    }

    public List<String> getWords() {
        return words;
    }

    public static TextReader getInstance() {
        if (instance == null) {
            return new TextReader();
        }
        return instance;
    }

    public static Stream<String> getStringsStream() {
        return getInstance()
                .getWords()
                .stream();
    }

    public static List<String> getStringsList() {
        return getInstance().getWords();
    }

}
