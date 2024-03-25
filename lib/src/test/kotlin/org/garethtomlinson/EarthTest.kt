package org.garethtomlinson

import kotlin.test.Test
import kotlin.test.assertEquals

class EarthTest {
    private val missionDetails = """
        5 3             // Size of Mars expressed as the upper bound of x y coordinates. The lower bound is 0 0. In this example Mars is a 6 x 4 grid. 
        1 1 E           // Landing location (x y) and initial orientation of the first Robot. 
        RFRFRFRF        // Commands for the first Robot. L = turn 90d left. R = turn 90d right. F = move forward one grid square if possible.
                        // A blank line denotes a new Robot being sent to Mars.
        3 2 N
        FRRFLLFFRRFLL
        
        0 3 W
        LLFFFLFLFL
    """

    @Test fun shouldExtractAMarsFromTheMissionDetails() {
        val mars = Earth.mars(missionDetails)
        assertEquals(expected = 6, actual = mars.width)
        assertEquals(expected = 4, actual = mars.height)
    }
}
