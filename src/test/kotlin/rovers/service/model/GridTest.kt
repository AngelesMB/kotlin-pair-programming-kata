package rovers.service.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GridTest {

    @Test
    fun isOccupiedShouldBeFalseIfGridIsEmpty() {
        val grid = Grid(Coordinate(5, 6))

        val result = grid.isOccupied(Coordinate(3, 4))

        assertFalse(result)
    }

    @Test
    fun shouldPlaceRover() {
        val grid = Grid(Coordinate(5, 6))
        val rover = Rover(Coordinate(3, 3), Orientation.EAST)

        val result = grid.placeRover(rover)

        assertTrue(result)
    }

    @Test
    fun shouldNotPlaceRoverIfCoordinateIsOccupied() {
        val grid = Grid(Coordinate(5, 6))
        val roverA = Rover(Coordinate(3, 3), Orientation.EAST)
        val roverB = Rover(Coordinate(3, 3), Orientation.EAST)

        val resultA = grid.placeRover(roverA)
        val resultB = grid.placeRover(roverB)

        assertTrue(resultA)
        assertFalse(resultB)
    }

    @Test
    fun shouldNotPlaceRoverIfOutOfBounds() {
        val grid = Grid(Coordinate(5, 6))
        val roverA = Rover(Coordinate(6, 7), Orientation.EAST)

        val resultA = grid.placeRover(roverA)

        assertFalse(resultA)
    }
}
