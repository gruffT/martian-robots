package org.garethtomlinson.exceptions

import org.garethtomlinson.Robot

class MissedPlanetException(robot: Robot) : Exception("Robot landed off planet at: ${robot.location.first} ${robot.location.second}")
