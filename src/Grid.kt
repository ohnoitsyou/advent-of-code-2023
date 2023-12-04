class Grid(val gridList: List<String>) {

    val gridString = gridList.joinToString("")

    private val gridWidth = gridList.first().length
    private val gridHeight = gridList.size

    companion object {
        val self = Pair(0, 0)
        val up = Pair(0, -1)
        val down = Pair(0, 1)
        val left = Pair(-1, 0)
        val right = Pair(1, 0)
        val upLeft = Pair(-1, -1)
        val upRight = Pair(1, -1)
        val downLeft = Pair(-1, 1)
        val downRight = Pair(1, 1)
    }

    fun pointAt(point: Pair<Int, Int>, dxdy: Pair<Int, Int> = self): Pair<Int, Int> {
        return Pair(point.first + dxdy.first, point.second + dxdy.second)
    }
    fun pointAt(idx: Int, dxdy: Pair<Int, Int> = self): Pair<Int, Int> {
        return pointAt(idxToPoint(idx), dxdy)
    }

    fun pointToIdx(point: Pair<Int, Int>): Int = point.second * gridWidth + point.first

    fun idxToPoint(idx: Int): Pair<Int, Int> {
        return Pair(idx % gridWidth, idx / gridWidth)
    }
    fun valueAt(point: Pair<Int, Int>, dxdy: Pair<Int, Int> = self): Char? {
        val newPoint = pointAt(point, dxdy)
        val idx = pointToIdx(newPoint)
        return if(isValidPoint(newPoint) && idx in 0 .. gridString.lastIndex) gridString[idx] else null
    }

    private fun isValidPoint(point: Pair<Int, Int>): Boolean  =
        point.first in 0 .. gridList.first().lastIndex && point.second in 0 .. gridList.size
}
