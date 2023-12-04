import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class GridTest {

    private val testGridList = listOf(".....0....",
                                      ".....1....",
                                      ".....2....",
                                      ".....3....",
                                      "....L^R...",
                                      "....<5>...",
                                      "....lvr...",
                                      ".....7....",
                                      ".....8....",
                                      ".....9....")
    private val testGridString = testGridList.joinToString("")
    private val grid = Grid(testGridList)

    @Test
    fun `Validate directions`() {
        assertEquals('5', grid.valueAt(Pair(5, 5)))
        assertEquals('^', grid.valueAt(Pair(5, 5), Grid.up))
        assertEquals('v', grid.valueAt(Pair(5, 5), Grid.down))
        assertEquals('<', grid.valueAt(Pair(5, 5), Grid.left))
        assertEquals('>', grid.valueAt(Pair(5, 5), Grid.right))
        assertEquals('L', grid.valueAt(Pair(5, 5), Grid.upLeft))
        assertEquals('R', grid.valueAt(Pair(5, 5), Grid.upRight))
        assertEquals('l', grid.valueAt(Pair(5, 5), Grid.downLeft))
        assertEquals('r', grid.valueAt(Pair(5, 5), Grid.downRight))
        assertEquals('5', grid.valueAt(Pair(5, 5), Grid.self))
    }

    @Test
    fun `No dxdy returns self`() {
        assertEquals(Pair(5, 5), grid.pointAt(Pair(5, 5)))
    }

    @Test
    fun `Convert point to idx`() {
        assertEquals(55, grid.pointToIdx(Pair(5, 5)))
    }

    @Test
    fun `Convert idx to point`() {
        assertEquals(Pair(5, 5), grid.idxToPoint(55))
    }

    @Test
    fun `Cannot move outside of grid`() {
        // start at upper left
        assertEquals(null, grid.valueAt(Pair(0, 0), Grid.left))
        assertEquals(null, grid.valueAt(Pair(0, 0), Grid.up))
        assertEquals(null, grid.valueAt(Pair(0, 0), Grid.upLeft))
        assertEquals(null, grid.valueAt(Pair(0, 0), Grid.downLeft))
        // start at lower right
        assertEquals(null, grid.valueAt(grid.idxToPoint(testGridString.lastIndex), Grid.right))
        assertEquals(null, grid.valueAt(grid.idxToPoint(testGridString.lastIndex), Grid.down))
        assertEquals(null, grid.valueAt(grid.idxToPoint(testGridString.lastIndex), Grid.downRight))
        assertEquals(null, grid.valueAt(grid.idxToPoint(testGridString.lastIndex), Grid.upRight))
        // Try to move past the first line
        assertEquals(null, grid.valueAt(Pair(testGridList.first().length, 0), Grid.right))
        // try to move before the second line
        assertEquals(null, grid.valueAt(Pair(0, 1), Grid.left))
    }
}