fun main() {
    fun partOne(input: List<String>): Int =
        input.map(CubeGame::fromString).fold(0) { count, game ->
            // 12 red cubes, 13 green cubes, and 14 blue cubes makes a valid game
            if(game.red <= 12 && game.green <= 13 && game.blue <= 14) {
                count + game.idx
            } else {
                count
            }
        }

    fun partTwo(input: List<String>): Int =
        input.map(CubeGame::fromString).sumOf { it.gamePower() }

    val input = readInputResource(false, "two.txt")
    println("AoC 2023 - Day 02:")
    "Part One: ${partOne(input)}".println()
    "Part Two: ${partTwo(input)}".println()
}

data class CubeGame(val idx: Int, val red: Int, val green: Int, val blue: Int) {
    fun gamePower() = red * green * blue
    companion object {
        private val greenRx = Regex("([0-9]+) green")
        private val redRx = Regex("([0-9]+) red")
        private val blueRx = Regex("([0-9]+) blue")
        private fun getValue(regex: Regex, input: String): Int =
            regex.findAll(input).sumOf {
                it.groups[1]?.value?.toInt() ?: 0
            }
        fun fromString(input: String ): CubeGame {
            val idx = """Game (\d+):""".toRegex().find(input)?.groups?.get(1)?.value?.toInt() ?: 0
            val rounds = input.split(";").map {
                Triple(getValue(redRx, it), getValue(greenRx, it), getValue(blueRx, it))
            }
            return CubeGame(idx, rounds.maxOf { it.first }, rounds.maxOf { it.second }, rounds.maxOf { it.third })
        }
    }
}
