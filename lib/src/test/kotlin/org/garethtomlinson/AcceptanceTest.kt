package org.garethtomlinson

import kotlin.test.Test
import kotlin.test.assertEquals

class AcceptanceTest {
    @Test fun shouldExecuteSampleDataWithExpectedResults() {
        val missionDetails = """
            5 3 
            1 1 E 
            RFRFRFRF
    
            3 2 N
            FRRFLLFFRRFLL
            
            0 3 W
            LLFFFLFLFL
        """

        val mars = Earth.mars(missionDetails)
        val missions = Earth.missions(missionDetails = missionDetails, mars = mars)

        val log: Log = Earth.expedition(missions, mars)
        assertEquals(
            expected =
                """
                1 1 E
                3 3 N LOST
                2 3 S
                """.trimIndent().trim(),
            actual = log.toString(),
        )
    }
}
