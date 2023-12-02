fun main() {
    val digitRegex = Regex("[0-9]")
    val words = listOf("one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9)
    val lookaheadRegex = """(?=(${words.joinToString("|") { it.first } + "|[0-9]"}))""".toRegex()

    fun partOne(lines: List<String>): Int = lines.sumOf { line ->
        digitRegex.findAll(line).let { matches ->
            "${matches.first().value}${matches.last().value}".toInt()
        }
    }

    fun partTwo(lines: List<String>): Int = lines.sumOf { line ->
        lookaheadRegex.findAll(line).let { matches ->
            listOf(matches.first().groupValues[1], matches.last().groupValues[1]).map { value ->
                words.first { word -> word.first == value || "${word.second}" == value }.second
            }
        }.joinToString("").toInt()
    }

    val input = readInputResource(false, "one.txt")
    println("AoC 2023 - Day 01:")
    "Part One: ${partOne(input)}".println()
    "Part Two: ${partTwo(input)}".println()
}
