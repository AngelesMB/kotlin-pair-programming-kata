package rovers.service

import rovers.service.model.Coordinate
import rovers.service.model.Grid
import rovers.service.model.Rover

class RoversService {
    var grid: Grid? = null
    fun createGrid(upperRightCorner: Coordinate): Grid {
        grid = Grid(upperRightCorner)
        return grid!!
    }

    fun placeRovers(rovers: List<Rover>): Grid {
        for (rover in rovers) {
            grid!!.placeRover(rover)
        }
        return grid!!
    }
}
