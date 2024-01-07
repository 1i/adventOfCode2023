package org.example;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.nio.file.Files.lines;

public class Utils {

    public static List<String> loadFile(String fileName) throws Exception {
        List<String> lines = new ArrayList<>();

        try (Stream<String> stream = lines(Paths.get(fileName))) {
            stream.forEach(line -> {
                lines.add(line);
            });
        }
        return lines;
    }

}
