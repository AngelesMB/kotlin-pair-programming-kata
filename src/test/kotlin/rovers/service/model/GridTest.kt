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
        val rover = Rover(Coordinate(3, 3), Orientation.EAST, listOf('R', 'M'))

        val result = grid.placeRover(rover)

        assertTrue(result)
    }

    @Test
    fun shouldNotPlaceRoverIfCoordinateIsOccupied() {
        val grid = Grid(Coordinate(5, 6))
        val roverA = Rover(Coordinate(3, 3), Orientation.EAST, listOf('R', 'M'))
        val roverB = Rover(Coordinate(3, 3), Orientation.EAST, listOf('R', 'M'))

        val resultA = grid.placeRover(roverA)
        val resultB = grid.placeRover(roverB)

        assertTrue(resultA)
        assertFalse(resultB)
    }

    @Test
    fun shouldNotPlaceRoverIfOutOfBounds() {
        val grid = Grid(Coordinate(5, 6))
        val roverA = Rover(Coordinate(6, 7), Orientation.EAST, listOf('R', 'M'))

        val resultA = grid.placeRover(roverA)

        assertFalse(resultA)
    }

    @Test
    fun shouldNotMoveRoverIfOutOfBounds() {
        val grid = Grid(Coordinate(5, 6))
        val rover = Rover(Coordinate(1, 2), Orientation.WEST, listOf('M', 'M', 'M'))
        val finalPosition = Coordinate(0, 2)

        grid.moveRover(rover)

        assertEquals(rover.position, finalPosition)
    }

    @Test
    fun shouldNotMoveRoverIfSlotIsOccupied() {
        val grid = Grid(Coordinate(5, 6))
        val roverA = Rover(Coordinate(0, 0), Orientation.EAST, listOf('M'))
        val roverB = Rover(Coordinate(2, 0), Orientation.WEST, listOf('M', 'M', 'M'))
        val finalPositionA = Coordinate(1, 0)
        val finalPositionB = Coordinate(2, 0)

        grid.moveRover(roverA)
        grid.moveRover(roverB)

        assertEquals(roverA.position, finalPositionA)
        assertEquals(roverB.position, finalPositionB)
    }
}
