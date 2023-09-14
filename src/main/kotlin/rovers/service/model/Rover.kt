package rovers.service.model

class Rover(var position: Coordinate, var orientation: Orientation) {
    fun executeInstruction(movement: Movement) {
        when (movement) {
            Movement.LEFT, Movement.RIGHT -> turn(movement)
            Movement.MOVE -> moveForward()
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
    }

    private fun moveForward() {
        when (orientation) {
            Orientation.NORTH -> position.y++
            Orientation.WEST -> position.x--
            Orientation.SOUTH -> position.y--
            Orientation.EAST -> position.x++
        }
    }
}
