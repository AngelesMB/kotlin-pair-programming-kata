package rovers.service.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class RoverTest {

    @ParameterizedTest
    @MethodSource("argumentForTurnLeftProvider")
    fun shouldTurn(direction: Movement, currentOrientation: Orientation, finalOrientation: Orientation) {
        val rover = Rover(Coordinate(5, 4), currentOrientation)

        rover.executeInstruction(direction)

        assertEquals(rover.orientation, finalOrientation)

    }

    @ParameterizedTest
    @MethodSource("argumentForMoveForwardProvider")
    fun shouldMoveForward(finalPosition: Coordinate, orientation: Orientation) {
        val rover = Rover(Coordinate(1, 1), orientation)

        rover.executeInstruction(Movement.MOVE)

        assertEquals(rover.position, finalPosition)
    }

    companion object {
        @JvmStatic
        fun argumentForTurnLeftProvider(): Stream<Arguments> {
            return Stream.of(
                arguments(Movement.LEFT, Orientation.NORTH, Orientation.WEST),
                arguments(Movement.LEFT, Orientation.WEST, Orientation.SOUTH),
                arguments(Movement.LEFT, Orientation.SOUTH, Orientation.EAST),
                arguments(Movement.LEFT, Orientation.EAST, Orientation.NORTH),
                arguments(Movement.RIGHT, Orientation.NORTH, Orientation.EAST),
                arguments(Movement.RIGHT, Orientation.EAST, Orientation.SOUTH),
                arguments(Movement.RIGHT, Orientation.SOUTH, Orientation.WEST),
                arguments(Movement.RIGHT, Orientation.WEST, Orientation.NORTH)
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
    }
}
