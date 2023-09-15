import rovers.service.RoversService
import rovers.service.model.Coordinate
import rovers.service.model.Orientation
import rovers.service.model.Rover

fun main() {
    val roversService = RoversService()
    val roverA = Rover(
        Coordinate(1, 2), Orientation.NORTH, listOf('L', 'M', 'L', 'M', 'L', 'M', 'L', 'M', 'M')
    )
    val roverB = Rover(
        Coordinate(3, 3), Orientation.EAST, listOf('M', 'M', 'R', 'M', 'M', 'R', 'M', 'R', 'R', 'M')
    )

    roversService.createGrid(Coordinate(5, 5))
    roversService.placeRovers(listOf(roverA, roverB))
    roversService.moveRovers()
    roversService.printResults()
}
