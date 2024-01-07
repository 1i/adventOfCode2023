import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// the Elf shows you a small bag and some cubes which are either red, green, or blue. Each time you play this game, he will hide a secret number of cubes of each color in the bag, and your goal is to figure out information about the number of cubes.
//
// To get information, once a bag has been loaded with cubes, the Elf will reach into the bag, grab a handful of random cubes, show them to you, and then put them back in the bag. He'll do this a few times per game.
//
// You play several games and record the information from each game (your puzzle input). Each game is listed with its ID number (like the 11 in Game 11: ...) followed by a semicolon-separated list of subsets of cubes that were revealed from the bag (like 3 red, 5 green, 4 blue).
//
// For example, the record of a few games might look like this:
// {g 1, b 3, r 4}, {g 1, r 1, g 2, b 6}, {g 1, g 2}
// Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
// Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
// Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
// Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
// Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
// In game 1, three sets of cubes are revealed from the bag (and then put back again). The first set is 3 blue cubes and 4 red cubes; the second set is 1 red cube, 2 green cubes, and 6 blue cubes; the third set is only 2 green cubes.
//
// The Elf would first like to know which games would have been possible if the bag contained only 12 red cubes, 13 green cubes, and 14 blue cubes?
//
// In the example above, games 1, 2, and 5 would have been possible if the bag had been loaded with that configuration. However, game 3 would have been impossible because at one point the Elf showed you 20 red cubes at once; similarly, game 4 would also have been impossible because the Elf showed you 15 blue cubes at once. If you add up the IDs of the games that would have been possible, you get 8.
//
// Determine which games would have been possible if the bag had been loaded with only 12 red cubes, 13 green cubes, and 14 blue cubes. What is the sum of the IDs of those games?


// --- Part Two ---
//As you continue your walk, the Elf poses a second question: in each game you played, what is the fewest number of cubes of each color that could have been in the bag to make the game possible?
//
//Again consider the example games from earlier:
//
//Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
//Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
//Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
//Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
//Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
//In game 1, the game could have been played with as few as 4 red, 2 green, and 6 blue cubes. If any color had even one fewer cube, the game would have been impossible.
//Game 2 could have been played with a minimum of 1 red, 3 green, and 4 blue cubes.
//Game 3 must have been played with at least 20 red, 13 green, and 6 blue cubes.
//Game 4 required at least 14 red, 3 green, and 15 blue cubes.
//Game 5 needed no fewer than 6 red, 3 green, and 2 blue cubes in the bag.
//The power of a set of cubes is equal to the numbers of red, green, and blue cubes multiplied together. The power of the minimum set of cubes in game 1 is 48. In games 2-5 it was 12, 1560, 630, and 36, respectively. Adding up these five powers produces the sum 2286.
//
//For each game, find the minimum set of cubes that must have been present. What is the sum of the power of these sets?
class game2 {

    String red = "red";
    String blue = "blue";
    String green = "green";

    String redRule = "12";
    String blueRule = "14";
    String greenRule = "13";

    String score = "(([1-9]) (red|blue|green))+";
    String digitSpaceWord = "((?:\\d+ \\w+)+)+";
    String clours = "red|blue|green";

    Map gameSet = new HashMap<String, Integer>();


    @Test
    void day2() {
        var fileName = "/Users/posu0001/workspace/playground/javaland/advent2023/advent2023/src/test/resources/day2Large.txt";
        //var fileName = "/Users/posu0001/workspace/playground/javaland/advent2023/advent2023/src/test/resources/day2.txt";
        var games = new ArrayList<Game>();
        var ref = new Object() {
            int gameCount = 0;
            int powerSum = 0;
        };
        Map map = new HashMap<String, Integer>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(line -> {
                String string = line.substring(0);
                //set map to 0
                map.put("red", 0);
                map.put("blue", 0);
                map.put("green", 0);
                ref.gameCount++;

                final var spaced = string.replaceAll(";", "").replaceAll(",", "").split(" ");
                int i = 0;
                int j = 1;
                int k = 0;
                while (k < spaced.length / 2) {
                    final int number = Integer.parseInt(spaced[i]);
                    final var colour = spaced[j];
                    i = i + 2;
                    j = j + 2;
                    k++;
                    // Keep track of the highest colour number
                    if ((int) map.get(colour) <= number) {
                        map.put(colour, number);
                    }
                }
                games.add(new Game(ref.gameCount, (int) map.get("red"), (int) map.get("green"), (int) map.get("blue")));
            });

            var validGames = games.stream().filter(x -> x.maxGreen <= 13 && x.maxBlue <= 14 && x.maxRed <= 12);
            final var sum = validGames.mapToInt(Game::getGameNumber).sum();
            System.out.println("valid game sum " + sum);

            games.forEach(g -> {
                ref.powerSum += (g.maxRed * g.maxGreen * g.maxRed);
            });
            System.out.println("sum of powers of each game " + ref.powerSum); //49710
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    class Game {
        int gameNumber;
        int maxRed;
        int maxGreen;
        int maxBlue;

        public Game(int gameNumber, int red, int green, int blue) {
            this.gameNumber = gameNumber;
            this.maxRed = red;
            this.maxGreen = green;
            this.maxBlue = blue;
        }

        public int getGameNumber() {
            return gameNumber;
        }

        public int getMaxRed() {
            return maxRed;
        }

        public int getMaxGreen() {
            return maxGreen;
        }

        public int getMaxBlue() {
            return maxBlue;
        }

        @Override
        public String toString() {
            return "Game{" +
                    "gameNumber=" + gameNumber +
                    ", red=" + maxRed +
                    ", green=" + maxGreen +
                    ", blue=" + maxBlue +
                    '}';
        }


    }
}
