package neu.cs.sacrifice.scene

import java.lang.IllegalArgumentException

class GameBoardImpl<T>(override val width: Int) : GameBoard<T> {

    val map = mutableMapOf<Cell, T?>()

    init {
        for(i in 1..width)
            for(j in 1..width)
                map[Cell(i,j)] = null
    }

    override operator fun get(cell: Cell): T? {
        if(!map.containsKey(cell)) return null;
        return map[cell]
    }

    override operator fun set(cell: Cell, value: T?) {
        map[cell] = value
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        return map.values.all(predicate)
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        return map.values.any(predicate)
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        for(entry in map){
            if(predicate.invoke(entry.value)) {
                return entry.key
            }
        }
        return null;
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        var list = mutableListOf<Cell>()
        for(entry in map){
            if(predicate.invoke(entry.value)) {
                list.add(entry.key)
            }
        }
        return list;
    }

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        if(i > width || j > width || i <= 0 || j <= 0) return null
        for(cell in map.keys)
            if(cell.i == i && cell.j == j) return cell
        return null;
    }

    override fun getCell(i: Int, j: Int): Cell {
        for(cell in map.keys)
            if(cell.i == i && cell.j == j) return cell
        throw IllegalArgumentException()
    }

    override fun getAllCells(): Collection<Cell> {
        var cells = mutableListOf<Cell>()
        for(i in 1..width)
            for(j in 1..width)
                getCellOrNull(i,j)?.let { cells.add(it) }
        return cells
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        var cells = mutableListOf<Cell>()
        for(n in jRange)
            getCellOrNull(i, n)?.let { cells.add(it) }
        return cells
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        var cells = mutableListOf<Cell>()
        for(n in iRange)
            getCellOrNull(n, j)?.let { cells.add(it) }
        return cells
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        val neighbor = when(direction) {
            Direction.DOWN -> getCellOrNull(i+1, j)
            Direction.LEFT -> getCellOrNull(i, j - 1)
            Direction.RIGHT -> getCellOrNull(i, j + 1)
            Direction.UP -> getCellOrNull(i - 1, j)
        }
        return neighbor
    }

}

fun createSquareBoard(width: Int): SquareBoard {
    return GameBoardImpl<Int>(width);
}
fun <T> createGameBoard(width: Int): GameBoard<T> {
    return GameBoardImpl<T>(width)
}

