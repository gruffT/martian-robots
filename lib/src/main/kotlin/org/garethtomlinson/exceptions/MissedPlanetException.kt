package org.garethtomlinson.exceptions

import org.garethtomlinson.Robot
import org.garethtomlinson.x
import org.garethtomlinson.y

class MissedPlanetException(robot: Robot) : Exception("Robot landed off planet at: ${robot.location.x()} ${robot.location.y()}")
