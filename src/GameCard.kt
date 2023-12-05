class GameCard(input: String) {
    private val winningNumbers: List<Int>
    private val yourNumbers: List<Int>
    private val gameNumber: Int

    // The init logic here can use some work with splits and such
    init {
        val colonIdx = input.indexOf(":")
        val barIdx = input.indexOf("|")
        gameNumber = input.substring(input.indexOfFirst { it.isDigit() }, colonIdx).toInt()
        winningNumbers = input.substring(colonIdx + 1, barIdx).trim().split(Regex("\\W+")).map { it.toInt() }
        yourNumbers = input.substring(barIdx + 1).trim().split(Regex("\\W+")).map { it.toInt() }
    }

    fun winCount(): Int = yourNumbers.count { it in winningNumbers }

    fun isWinner(): Pair<Boolean, Int> {
        val winCount = winCount()
        val points = when {
            winCount > 1 -> List(winCount - 1) { it }.fold(1) { acc, _ -> acc * 2 }
            winCount == 1 -> 1
            else -> 0
        }
        return Pair(points != 0 , points)
    }

}
