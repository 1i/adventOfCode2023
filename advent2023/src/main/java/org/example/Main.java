package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        var fileName = "/Users/posu0001/workspace/playground/javaland/advent2023/advent2023/src/main/resources/largeInput.txt";
        // this is terrible
        var ref = new Object() {
            int total = 0;
        };
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(string -> {
                System.out.println(string);
                final var numbers = string.chars()
                        .mapToObj(item -> (char) item)
                        .filter(Character::isDigit)
                        .collect(Collectors.toList());
                if (numbers.size() == 1) {
                    numbers.add(numbers.get(0));
                }
                System.out.println("output " + numbers.get(0) + numbers.get(numbers.size() - 1));
                // type conversion is annoying
                String i = String.valueOf(numbers.get(0)) + String.valueOf(numbers.get(numbers.size() - 1));
                ref.total += Integer.parseInt(i);
            });
            System.out.println("total " + ref.total);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}