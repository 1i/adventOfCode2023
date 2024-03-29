package org.example

import org.junit.jupiter.api.Assertions.*
import org.assertj.core.api.Assertions.assertThat
import org.example.Resources.resourceAsListOfString
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 4")
class Day4Test {

    // Arrange
    private val input = """
        Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
        Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
        Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
        Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
        Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
        Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
        """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // Act
            //val answer = Day4(input).solvePart1()
            val answer = Day4(resourceAsListOfString("day4.txt")).solvePart1()


            // Assert
            assertThat(answer).isEqualTo(13)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day4(resourceAsListOfString("cards.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(19_135)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day4(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(30)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day4(resourceAsListOfString("cards.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(5747443)
        }
    }
}