import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.*;

/**
 * As far as the Elf has been able to figure out, you have to figure out which of the numbers you have appear in the list of winning numbers.
 * The first match makes the card worth one point and each match after the first doubles the point value of that card.
 * <p>
 * For example:
 * <p>
 * Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
 * Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
 * Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
 * Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
 * Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
 * Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
 * In the above example, card 1 has five winning numbers (41, 48, 83, 86, and 17) and eight numbers you have (83, 86, 6, 31, 17, 9, 48, and 53).
 * Of the numbers you have, four of them (48, 83, 17, and 86) are winning numbers! That means card 1 is worth 8 points (1 for the first match,
 * then doubled three times for each of the three matches after the first).
 * <p>
 * Card 2 has two winning numbers (32 and 61), so it is worth 2 points.
 * Card 3 has two winning numbers (1 and 21), so it is worth 2 points.
 * Card 4 has one winning number (84), so it is worth 1 point.
 * Card 5 has no winning numbers, so it is worth no points.
 * Card 6 has no winning numbers, so it is worth no points.
 * So, in this example, the Elf's pile of scratchcards is worth 13 points.
 */
public class Day4 {

    String fileName = "/Users/posu0001/workspace/playground/javaland/advent2023/advent2023/src/test/resources/Day4Small.txt";
    //String fileName = "/Users/posu0001/workspace/playground/javaland/advent2023/advent2023/src/test/resources/day4.txt";
    private int wins = 0;
    private int total = 0;

    int stopper = 10;
    List<String> lines = new ArrayList<>();

    @Test
    void exampleTest() throws Exception {

        try (Stream<String> stream = lines(Paths.get(fileName))) {
            wins = 0;
            stream.forEach(line -> {
                lines.add(line);
            });
        }
        for (int i = 0; i < lines.size() - 1; i++) {

            checkWinningLine(lines.get(i), i);
        }
        System.out.println("cards.sum" + lines.size());
    }

    private void checkWinningLine(final String line, final int i) {
        line.replaceFirst("Game.*:", "");
        final var split = line.split("\\|");
        final var winning = split[0].split(" ");
        final var picks = split[1].split(" ");
        for (String winner : winning) {
            for (String pick : picks) {
                if (pick.equalsIgnoreCase(winner) && !pick.isBlank()) {
                    wins++;
                }
            }
        }
        for (int j = 1; j <= wins; j++) {
            lines.add(lines.get(i + j));
        }
        wins = 0;
    }

    int getPoints(int x) {
        if (x == 0) {
            return 0;
        }
        int points = 1;
        for (int i = 1; i < x; i++) {
            points = points * 2;
        }
        //System.out.println("points " + points);
        return points;
    }

    /**
     * --- Part Two ---
     * There's no such thing as "points". Instead, scratchcards only cause you to win more scratchcards equal to the number of winning numbers you have.
     * <p>
     * Specifically, you win copies of the scratchcards below the winning card equal to the number of matches.
     * So, if card 10 were to have 5 matching numbers, you would win one copy each of cards 11, 12, 13, 14, and 15.
     * <p>
     * Copies of scratchcards are scored like normal scratchcards and have the same card number as the card they copied.
     * So, if you win a copy of card 10 and it has 5 matching numbers, it would then win a copy of the same cards that the original card 10 won: cards 11, 12, 13, 14, and 15.
     * This process repeats until none of the copies cause you to win any more cards. (Cards will never make you copy a card past the end of the table.)
     * <p>
     * This time, the above example goes differently:
     * <p>
     * Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
     * Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
     * Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
     * Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
     * Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
     * Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
     * <p>
     * Card 1 has four matching numbers, so you win one copy each of the next four cards: cards 2, 3, 4, and 5.
     * Your original card 2 has two matching numbers, so you win one copy each of cards 3 and 4.
     * Your copy of card 2 also wins one copy each of cards 3 and 4.
     * Your four instances of card 3 (one original and three copies) have two matching numbers, so you win four copies each of cards 4 and 5.
     * Your eight instances of card 4 (one original and seven copies) have one matching number, so you win eight copies of card 5.
     * Your fourteen instances of card 5 (one original and thirteen copies) have no matching numbers and win no more cards.
     * Your one instance of card 6 (one original) has no matching numbers and wins no more cards.
     * Once all of the originals and copies have been processed, you end up with 1 instance of card 1, 2 instances of card 2, 4 instances of card 3, 8 instances of card 4, 14 instances of card 5, and 1 instance of card 6. In total, this example pile of scratchcards causes you to ultimately have 30 scratchcards!
     * <p>
     * Process all of the original and copied scratchcards until no more scratchcards are won. Including the original set of scratchcards, how many total scratchcards do you end up with?
     */
    @Test
    void part2Test() {

    }
}
