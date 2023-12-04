fun main() {

    fun findPartNumbers(grid: Grid): List<IntRange> {
        return grid.searchGridString(Regex("""[0-9]+"""))
    }

    fun findGears(grid: Grid): List<Pair<Int, Int>> {
        return grid.searchGridString(Regex("""\*""")).map { range ->
            grid.pointAt(range.first()) // gears are single characters
        }
    }

    fun isValidPart(grid: Grid, partLocation: IntRange): Boolean {
        return partLocation.any { idx ->
            val point = grid.pointAt(idx)
            val candidatePositions = mutableListOf(grid.pointAt(point, Grid.up), grid.pointAt(point, Grid.down))
            if(idx == partLocation.first()) {
                candidatePositions.addAll(listOf(grid.pointAt(point, Grid.left), grid.pointAt(point, Grid.upLeft), grid.pointAt(point, Grid.downLeft)))
            }
            if(idx == partLocation.last()) {
                candidatePositions.addAll(listOf(grid.pointAt(point, Grid.right), grid.pointAt(point, Grid.upRight), grid.pointAt(point, Grid.downRight)))
            }
            val validNeighborChars = candidatePositions.mapNotNull { position ->
                grid.valueAt(position)
            }
            // Are any neighboring cells not a number and not a .
            validNeighborChars.any { character -> character.toString() !in (List(10) { "$it"} + listOf(".")) }
        }
    }

    fun isValidGear(grid: Grid, gearLoc: Pair<Int, Int>, partLocations: List<IntRange>): Pair<Boolean, Int> {
        val candidateLocations = listOfNotNull(
            grid.pointAt(gearLoc, Grid.upLeft),
            grid.pointAt(gearLoc, Grid.up),
            grid.pointAt(gearLoc, Grid.upRight),
            grid.pointAt(gearLoc, Grid.right),
            grid.pointAt(gearLoc, Grid.downRight),
            grid.pointAt(gearLoc, Grid.down),
            grid.pointAt(gearLoc, Grid.downLeft),
            grid.pointAt(gearLoc, Grid.left)
        )
        val neighbors = candidateLocations.flatMap { point ->
            val idx = grid.pointToIdx(point)
            val foundNeighbors = partLocations.filter { it.contains(idx) }
            val neighborNumbers = foundNeighbors.map {neighbor ->
                grid.gridString.substring(neighbor)
            }
            neighborNumbers
        }.distinct()
        return Pair(neighbors.size == 2, neighbors.fold(1) { product, value -> product * value.toInt()})
    }

    fun partOne(grid: Grid): Int {
        val numberLocations = findPartNumbers(grid)
        return numberLocations.sumOf { partLocation ->
            val isValidPart = isValidPart(grid, partLocation)
            if(isValidPart) {
                partLocation.fold("") { str, idx ->
                    str + grid.valueAt(grid.idxToPoint(idx))
                }.toInt()
            } else {
                0
            }
        }
    }


    fun partTwo(grid: Grid): Int {
        val gearLocations = findGears(grid)
        val partLocations = findPartNumbers(grid)
        return gearLocations.sumOf { gearLoc ->
            val (valid, ratio ) = isValidGear(grid, gearLoc, partLocations)
            if(valid) ratio else 0
        }
    }

    val input = readInputResource(false, "three.txt")
    val grid = Grid(input)
    println("AoC 2023 - Day 03:")
    "Part One: ${partOne(grid)}".println()
    "Part Two: ${partTwo(grid)}".println()
}

fun Grid.searchGridString(needle: Regex): List<IntRange> {
    return needle.findAll(gridString).map { it.range }.toList()
}
