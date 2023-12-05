
fun main() {

    fun partOne(gameCards: List<GameCard>): Int {
        return gameCards.sumOf { it.isWinner().second }
    }

    // Original solution from https://github.com/Will-McBurney/AOC-2023/blob/master/src/main/kotlin/year23/day4/Main.kt
    // I was on a similar path, I could get the sample, but the full was not quite right
    // Hint was in the way the prompt reads, mentions things like "one original and 3 copies"
    // didn't need to process all the copies, just know that when you process a card,
    //   it adds the same number of each downstream
    fun partTwo(gameCards: List<GameCard>): Int {
        val startingCards = gameCards.map { 1 }.toMutableList()
        startingCards.indices.forEach { cardIdx ->
            // add the count of the current card to each card downstream
            ((cardIdx + 1) until (cardIdx + 1 + gameCards[cardIdx].winCount())).forEach { idx ->
                startingCards[idx] += startingCards[cardIdx]
            }
        }
        return startingCards.sum()
    }

    val input = readInputResource(false, "four.txt")
    val gameCards = input.map { GameCard(it) }
    println("AoC 2023 - Day 04:")
    "Part One: ${partOne(gameCards)}".println()
    "Part Two: ${partTwo(gameCards)} -> 6857330".println()
}
