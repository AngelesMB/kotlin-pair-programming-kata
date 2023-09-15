package rovers.service.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class RoverTest {
    val occupiedSlots: MutableSet<Coordinate> = mutableSetOf(Coordinate(1, 4))
    val maxCoordinate: Coordinate = Coordinate(5, 6)

    @ParameterizedTest
    @MethodSource("argumentForTurnProvider")
    fun shouldTurn(direction: List<Char>, currentOrientation: Orientation, finalOrientation: Orientation) {
        val rover = Rover(Coordinate(5, 4), currentOrientation, direction)

        rover.executeInstructions(occupiedSlots, maxCoordinate)

        assertEquals(rover.orientation, finalOrientation)
    }

    @ParameterizedTest
    @MethodSource("argumentForMoveForwardProvider")
    fun shouldMoveForward(finalPosition: Coordinate, orientation: Orientation) {
        val rover = Rover(Coordinate(1, 1), orientation, listOf('M'))

        rover.executeInstructions(occupiedSlots, maxCoordinate)

        assertEquals(rover.position, finalPosition)
    }

    @ParameterizedTest
    @MethodSource("argumentForMoveForwardNotPossibleProvider")
    fun shouldNotMoveForwardIfNotPossible(finalPosition: Coordinate, orientation: Orientation) {
        val rover = Rover(Coordinate(0, 0), orientation, listOf('M', 'M', 'M', 'M', 'M', 'M', 'M', 'M'))

        rover.executeInstructions(occupiedSlots, maxCoordinate)

        assertEquals(rover.position, finalPosition)
    }

    companion object {
        @JvmStatic
        fun argumentForTurnProvider(): Stream<Arguments> {
            return Stream.of(
                arguments(listOf('L'), Orientation.NORTH, Orientation.WEST),
                arguments(listOf('L'), Orientation.WEST, Orientation.SOUTH),
                arguments(listOf('L'), Orientation.SOUTH, Orientation.EAST),
                arguments(listOf('L'), Orientation.EAST, Orientation.NORTH),
                arguments(listOf('R'), Orientation.NORTH, Orientation.EAST),
                arguments(listOf('R'), Orientation.EAST, Orientation.SOUTH),
                arguments(listOf('R'), Orientation.SOUTH, Orientation.WEST),
                arguments(listOf('R'), Orientation.WEST, Orientation.NORTH)
            )
        }

        @JvmStatic
        fun argumentForMoveForwardProvider(): Stream<Arguments> {
            return Stream.of(
                arguments(Coordinate(1, 2), Orientation.NORTH),
                arguments(Coordinate(2, 1), Orientation.EAST),
                arguments(Coordinate(1, 0), Orientation.SOUTH),
                arguments(Coordinate(0, 1), Orientation.WEST),
            )
        }

        @JvmStatic
        fun argumentForMoveForwardNotPossibleProvider(): Stream<Arguments> {
            return Stream.of(
                arguments(Coordinate(0, 6), Orientation.NORTH),
                arguments(Coordinate(5, 0), Orientation.EAST),
                arguments(Coordinate(0, 0), Orientation.SOUTH),
                arguments(Coordinate(0, 0), Orientation.WEST),
            )
        }
    }

    @Test
    fun shouldExecuteInstructionsWhilePossible() {
        val instructions = listOf('R', 'M')
        val rover = Rover(Coordinate(1, 1), Orientation.NORTH, instructions)
        val expectedResponse = true

        val result = rover.executeInstructions(occupiedSlots, maxCoordinate)

        assertEquals(result, expectedResponse)
    }

    @Test
    fun shouldStopExecutingInstructionsWhenNotPossible() {
        val instructions = listOf('R', 'M', 'L', 'L', 'M', 'M', 'M', 'M')
        val rover = Rover(Coordinate(1, 1), Orientation.NORTH, instructions)
        val expectedResponse = false

        val result = rover.executeInstructions(occupiedSlots, maxCoordinate)

        assertEquals(result, expectedResponse)
    }

    @Test
    fun shouldMapInstructionsToMovements() {
        val instructions = listOf('R', 'M')
        val rover = Rover(Coordinate(5, 4), Orientation.NORTH, instructions)
        val mappedMovements = listOf(Movement.RIGHT, Movement.MOVE)

        rover.executeInstructions(occupiedSlots, maxCoordinate)

        assertEquals(rover.requiredMovements, mappedMovements)
    }

    @Test
    fun isMovementForwardPossibleShouldBeTrueIfNoObstacles() {
        val rover = Rover(Coordinate(5, 4), Orientation.NORTH, listOf('M'))

        val result = rover.isMovementForwardPossible(occupiedSlots, maxCoordinate)

        assertTrue(result)
    }

    @Test
    fun isMovementForwardPossibleShouldBeFalseIfOutOfBounds() {
        val rover = Rover(Coordinate(5, 6), Orientation.NORTH, listOf('M'))

        val result = rover.isMovementForwardPossible(occupiedSlots, maxCoordinate)

        assertFalse(result)
    }

    @Test
    fun isMovementForwardPossibleShouldBeFalseIfSlotIsOccupied() {
        val rover = Rover(Coordinate(0, 4), Orientation.EAST, listOf('M'))

        val result = rover.isMovementForwardPossible(occupiedSlots, maxCoordinate)

        assertFalse(result)
    }
}
