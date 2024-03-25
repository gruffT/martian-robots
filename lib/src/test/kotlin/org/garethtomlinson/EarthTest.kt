package org.garethtomlinson

import kotlin.test.Test
import kotlin.test.assertEquals

class EarthTest {
    private val missionDetails = """
        5 3 
        1 1 E 
        RFRFRFRF

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
