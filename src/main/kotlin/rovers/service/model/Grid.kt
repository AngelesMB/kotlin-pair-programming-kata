package rovers.service.model

class Grid(val maxCoordinate: Coordinate) {
    var occupiedSlots: MutableSet<Coordinate> = mutableSetOf()

    fun isOccupied(position: Coordinate): Boolean {
        return occupiedSlots.find { coord -> coord == position } != null
    }

    fun placeRover(rover: Rover): Boolean {
        if (rover.position.x <= maxCoordinate.x && rover.position.y <= maxCoordinate.y) {
            return occupiedSlots.add(rover.position)
        } else {
            return false
        }
    }

    fun moveRover(rover: Rover) {
        rover.executeInstructions(occupiedSlots, maxCoordinate)
        placeRover(rover)
    }
}
