package rovers.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import rovers.service.model.Coordinate
import rovers.service.model.Orientation
import rovers.service.model.Rover

class RoversServiceTest {

    private val roversService = RoversService()
    private val maxCoordinate = Coordinate(5, 6)

    @Test
    fun shouldCreateGridFromMaxCoordinate() {
        assertEquals(maxCoordinate, roversService.createGrid(maxCoordinate).maxCoordinate)
    }

    @Test
    fun shouldPlaceAListOfRoversInGrid() {
        val grid = roversService.createGrid(maxCoordinate)
        val startingPositionA = Coordinate(1, 2)
        val startingPositionB = Coordinate(3, 2)
        val rovers = listOf(
            Rover(startingPositionA, Orientation.NORTH, listOf('R', 'M')),
            Rover(startingPositionB, Orientation.SOUTH, listOf('R', 'M'))
        )

        roversService.placeRovers(rovers)

        assertTrue(grid.isOccupied(startingPositionA))
        assertTrue(grid.isOccupied(startingPositionB))
    }

    @Test
    fun shouldMoveRoversWithNoObstacles() {
        val grid = roversService.createGrid(maxCoordinate)
        val roverA = Rover(Coordinate(1, 2), Orientation.NORTH, listOf('R', 'M'))
        val roverB = Rover(Coordinate(3, 3), Orientation.EAST, listOf('R', 'M'))
        val finalPositionA = Coordinate(2, 2)
        val finalPositionB = Coordinate(3, 2)

        roversService.placeRovers(listOf(roverA, roverB))
        roversService.moveRovers()

        assertTrue(grid.isOccupied(finalPositionA))
        assertTrue(grid.isOccupied(finalPositionB))
        assertEquals(roverA.position, finalPositionA)
        assertEquals(roverB.position, finalPositionB)
    }

    @Test
    fun shouldMoveRoversWhilePossible() {
        val grid = roversService.createGrid(maxCoordinate)
        val roverA = Rover(Coordinate(1, 2), Orientation.NORTH, listOf('R', 'M'))
        val roverB = Rover(Coordinate(2, 3), Orientation.EAST, listOf('R', 'M'))
        val finalPositionA = Coordinate(2, 2)
        val finalPositionB = Coordinate(2, 3)

        roversService.placeRovers(listOf(roverA, roverB))
        roversService.moveRovers()

        assertTrue(grid.isOccupied(finalPositionA))
        assertTrue(grid.isOccupied(finalPositionB))
        assertEquals(roverA.position, finalPositionA)
        assertEquals(roverB.position, finalPositionB)
    }
}
