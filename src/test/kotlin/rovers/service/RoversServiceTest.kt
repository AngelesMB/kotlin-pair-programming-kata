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
        val rovers = listOf(Rover(startingPositionA, Orientation.NORTH), Rover(startingPositionB, Orientation.SOUTH))

        roversService.placeRovers(rovers)

        assertTrue(grid.isOccupied(startingPositionA))
        assertTrue(grid.isOccupied(startingPositionB))
    }
}
