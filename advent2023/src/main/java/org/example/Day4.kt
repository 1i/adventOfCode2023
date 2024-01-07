package org.example

import kotlin.math.pow

class Day4(input: List<String>) {

    private val cardMatches = input.map { parseCard(it) }

    fun solvePart1(): Int =
        cardMatches.sumOf { 2.0.pow(it-1).toInt() }

    fun solvePart2(): Int {
        val cards = IntArray(cardMatches.size) { 1 }
        cardMatches.forEachIndexed { index, score ->
            repeat(score) {
                cards[index+it+1] += cards[index]
            }
        }
        println("cards.sum " +cards.sum())
        return cards.sum()
    }

    private fun parseCard(input: String): Int {
        val winningNumbers = input.substringAfter(":").substringBefore("|").split(" ").filter { it.isNotEmpty() }.toSet()
        val ourNumbers = input.substringAfter("|").split(" ").filter { it.isNotEmpty() }.toSet()
        return winningNumbers.intersect(ourNumbers).size
    }
}