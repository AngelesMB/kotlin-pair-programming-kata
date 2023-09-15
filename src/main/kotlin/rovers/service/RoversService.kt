package rovers.service

import rovers.service.model.Coordinate
import rovers.service.model.Grid
import rovers.service.model.Rover

class RoversService {
    var grid: Grid? = null
    val rovers: MutableList<Rover> = mutableListOf()
    fun createGrid(upperRightCorner: Coordinate): Grid {
        grid = Grid(upperRightCorner)
        return grid!!
    }

    fun placeRovers(providedRovers: List<Rover>): Grid {
        for (rover in providedRovers) {
            if (grid!!.placeRover(rover)) {
                rovers.add(rover)
            }
        }
        return grid!!
    }

    fun moveRovers(): Grid {
        for (rover in rovers) {
            grid!!.moveRover(rover)
        }
        return grid!!
    }

    fun printResults() {
        for (rover in rovers) {
            println("${rover.position.x} ${rover.position.y} ${rover.orientation}")
        }
    }
}
