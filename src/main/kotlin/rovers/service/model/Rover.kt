package rovers.service.model

class Rover(var position: Coordinate, var orientation: Orientation, val instructions: List<Char>) {
    val requiredMovements: MutableList<Movement> = mutableListOf()
    var performedMovementsCount: Int = 0
    fun executeInstructions(occupiedSlots: MutableSet<Coordinate>, maxCoordinate: Coordinate): Boolean {
        mapInstructionsToMovements()
        for (movement in requiredMovements) {
            when (movement) {
                Movement.LEFT, Movement.RIGHT -> turn(movement)
                Movement.MOVE -> moveForward(occupiedSlots, maxCoordinate)
            }
        }
        return performedMovementsCount == requiredMovements.size
    }

    private fun mapInstructionsToMovements() {
        for (char in instructions) {
            when (char) {
                'L' -> requiredMovements.add(Movement.LEFT)
                'R' -> requiredMovements.add(Movement.RIGHT)
                'M' -> requiredMovements.add(Movement.MOVE)
            }
        }
    }

    private fun turn(direction: Movement) {
        if (direction == Movement.LEFT) {
            orientation = when (orientation) {
                Orientation.NORTH -> Orientation.WEST
                Orientation.WEST -> Orientation.SOUTH
                Orientation.SOUTH -> Orientation.EAST
                Orientation.EAST -> Orientation.NORTH
            }
        } else if (direction == Movement.RIGHT) {
            orientation = when (orientation) {
                Orientation.NORTH -> Orientation.EAST
                Orientation.EAST -> Orientation.SOUTH
                Orientation.SOUTH -> Orientation.WEST
                Orientation.WEST -> Orientation.NORTH
            }
        }
        performedMovementsCount++
    }

    private fun moveForward(occupiedSlots: MutableSet<Coordinate>, maxCoordinate: Coordinate): Boolean {
        if (isMovementForwardPossible(occupiedSlots, maxCoordinate)) {
            when (orientation) {
                Orientation.NORTH -> position.y++
                Orientation.WEST -> position.x--
                Orientation.SOUTH -> position.y--
                Orientation.EAST -> position.x++
            }
            performedMovementsCount++
            return true
        } else {
            return false
        }
    }

    fun isMovementForwardPossible(occupiedSlots: MutableSet<Coordinate>, maxCoordinate: Coordinate): Boolean {
        val potentialPosition: Coordinate = when (orientation) {
            Orientation.NORTH -> Coordinate(position.x, position.y + 1)
            Orientation.WEST -> Coordinate(position.x - 1, position.y)
            Orientation.SOUTH -> Coordinate(position.x, position.y - 1)
            Orientation.EAST -> Coordinate(position.x + 1, position.y)
        }
        return (potentialPosition.x <= maxCoordinate.x && potentialPosition.x >= 0)
                && (potentialPosition.y <= maxCoordinate.y && potentialPosition.y >= 0)
                && occupiedSlots.find { coord -> coord == potentialPosition } == null
    }
}
