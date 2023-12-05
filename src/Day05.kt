fun main() {
    fun partTwo(input: Almanac): Int {
        return 0
    }

    fun partOne(input: Almanac): Int {
        return 0
    }

    val input = readInputResource(false, "five.txt")
    println("AoC 2023 - Day 05:")
    "Part One: ${partOne(Almanac(input))}".println()
    "Part Two: ${partTwo(Almanac(input))}".println()
}


class Almanac(input: List<String>) {
    val seeds = input.first().substringAfter(": ").split(" ").map { it.trim().toBigInteger() }
}
