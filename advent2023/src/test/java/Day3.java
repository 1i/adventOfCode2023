//The engineer explains that an engine part seems to be missing from the engine, but nobody can figure out which one.
// If you can add up all the part numbers in the engine schematic, it should be easy to work out which part is missing.
//
//The engine schematic (your puzzle input) consists of a visual representation of the engine.
// There are lots of numbers and symbols you don't really understand,
// but apparently any number adjacent to a symbol,
// even diagonally, is a "part number" and should be included in your sum.
// (Periods (.) do not count as a symbol.)
//
//Here is an example engine schematic: 11 x 11
//
// 467..114..
// ...*......
// ..35..633.
// ......#...
// 617*......
// .....+.58.
// ..592.....
// ......755.
// ...$.*....
// .664.598..

//In this schematic, two numbers are not part numbers because they are not adjacent to a symbol: 114 (top right) and 58 (middle right). Every other number is adjacent to a symbol and so is a part number; their sum is 4361.
//
//Of course, the actual engine schematic is much larger. What is the sum of all of the part numbers in the engine schematic?


import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

// 140 x 140
public class Day3 {

    // 467..114..
// ...*......
// ..35..633.
// ......#...
// 617*......
// .....+.58.
// ..592.....
// ......755.
// ...$.*....
// .664.598..
    String fileName = "/Users/posu0001/workspace/playground/javaland/advent2023/advent2023/src/test/resources/Day3SmallEngine.txt";

    @Test
    void lineReader() throws Exception {

        var ref = new Object() {
            int count = 0;
        };
        List<Pair> gears = new ArrayList<>();
        List<Pair> parts = new ArrayList<>();

        String[][] engineMap = new String[10][10];
        char[] characters = new char[10];
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(line -> {

                char[] charArray = line.replaceAll("#|\\*|\\$|\\+", "X").toCharArray();
                int engineSize = 10;
                for (int x = ref.count; x < engineSize; x++) {
                    for (int y = 0; y < engineSize; y++) {
                        final var piece = String.valueOf(charArray[y]);
                        engineMap[x][y] = piece;

                        if (piece.equalsIgnoreCase("X")) {
                            gears.add(new Pair<>(x, y));
                        }

                        if (piece.matches("[0-9]")) {
                            parts.add(new Pair<>(x, y));

                        }
                    }
                }
                System.out.println(Arrays.toString(charArray));

                ref.count++;
            });
            System.out.println("parts " + parts);
            System.out.println("gears " + gears);
            //printMap(engineMap);
            System.out.println(nearByPairs(gears.get(0), parts.get(3)));
            System.out.println(nearByPairs(gears.get(0), parts.get(1)));
            //findNeighbour(engineMap,gears.get(0));
            for (Pair gear : gears) {
                for (Pair part : parts) {
                    if (nearByPairs(gear, part)) {
                        //System.out.println("gear " + gear);
                         System.out.println("part " + part);
                    }
                }
                //findNeighbour(engineMap,gear);
            }

        }
    }

    List<Pair> findNeighbour(String[][] map, Pair<Integer, Integer> point) {
        List<Pair> neighbours = new ArrayList<>();
        var content = new StringBuilder();
        int x = point.x;
        int y = point.y;
        final var item = map[point.x][point.y];
        //content.append("x = " + point.x + " y = " + y + " value " + map[x][y] + " item " + item);
        System.out.println(content);
        try {
            for (int i = 1; i >= -1; i--) {
                for (int j = 1; j >= -1; j--) {
                    final var positiveX = x - i;
                    final var positiveY = y - j;
                    final var piece = map[positiveX][positiveY];
                    System.out.println(piece);
                    if (piece.matches("[0-9]")) {
                        neighbours.add(new Pair<>(positiveX, positiveY));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("meh " + e);
        }
        System.out.println("neighbours are " + neighbours);
        return neighbours;
    }

    @Test
    void SmallEngineTest() throws IOException {

        List<Pair> gears = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String[][] engine = new String[10][10];
        StringBuilder content = new StringBuilder();

        int engineSize = 10;
        for (int x = 0; x < engineSize; x++) {
            //content.append("|");
            for (int y = 0; y < engineSize; y++) {
                String readChar = String.valueOf((char) reader.read());
                if (isNumeric(readChar) || isDotOrNewLine(readChar)) {
                    // empty space
                    content.append(readChar);
                    if (!readChar.isBlank()) {
                        engine[x][y] = readChar;
                    }
                }
                // convert all other gears to *
                else {
                    if (!readChar.isBlank()) {
                        gears.add(new Pair<>(x, y));
                        engine[x][y] = "*";
                        content.append("*");
                    }
                }
//                    engine[x][y] = readChar;
//                content.append(readChar);
                if (y == engineSize - 1) {
                    //content.append("|");
                }
            }
        }

        System.out.println("content \n" + content);
        System.out.println("gears " + gears);
        printMap(engine);
    }


    String printMap(String[][] map) {
        System.out.println("Printing engine");
        int engineSize = 10;

        System.out.print("Map is ");
        System.out.println("X " + engineSize + " Y " + engineSize);
        StringBuilder content = new StringBuilder();

        content.append("y".repeat(engineSize)).append("\n");
        content.append("-".repeat(engineSize)).append("\n");
        for (int x = 0; x < engineSize; x++) {
            //content.append("x " + x + " | ");
            for (int y = 0; y < engineSize; y++) {
                content.append("x = " + x + " y = " + y + " value " + map[x][y] + "\n");
                //content.append(map[x][y]);
            }
            content.append("------------------- \n");
        }
        System.out.println(content);

        return content.toString();
    }

    boolean nearByPairs(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {

        final var xs = p1.x - p2.x;
        final var ys = p1.y - p2.y;

        final var distance = Math.hypot(p1.x - p2.x, p1.y - p2.y);
        return (distance > 0 && distance <= 2);
    }

    class Pair<L, R> {

        private final L x;
        private final R y;

        public Pair(L x, R y) {
            assert x != null;
            assert y != null;

            this.x = x;
            this.y = y;
        }

        public L getLeft() {
            return x;
        }

        public R getRight() {
            return y;
        }

        @Override
        public int hashCode() {
            return x.hashCode() ^ y.hashCode();
        }

        @Override
        public String toString() {
            return "{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    record Gear() {
        static int x;
        static int y;
    }

    class EnginePart {

        int number;

        // line number
        int x;

        // characters in
        int y;
        boolean valid;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static boolean isDotOrNewLine(String str) {
        return str.matches("(\\.)|(\n)");
    }

    public static boolean isControlChar(String str) {
        return str.matches("%");
    }
}
