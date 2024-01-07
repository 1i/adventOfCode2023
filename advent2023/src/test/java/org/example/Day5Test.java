package org.example;

import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.nio.file.Files.lines;

class Day5Test {

    String example = "/Users/posu0001/workspace/playground/javaland/advent2023/advent2023/src/test/resources/day5Example.txt";
    String fileName = "/Users/posu0001/workspace/playground/javaland/advent2023/advent2023/src/test/resources/day5.txt";
    int stopper = 10;

    @Test
    void solveExample() throws Exception {
        List<String> strings = Utils.loadFile(example);

        strings.forEach(System.out::println);
    }

    //@Test
    void solveLarge() throws Exception {
        List<String> strings = Utils.loadFile(fileName);
        strings.forEach(System.out::println);
    }

}