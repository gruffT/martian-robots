@file:Suppress("ktlint:standard:no-wildcard-imports")

package org.garethtomlinson

import org.garethtomlinson.exceptions.BadConfigurationException
import kotlin.test.*

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

    @Test fun shouldThrowBadConfigurationExceptionIfMissionDetailsEmpty() {
        val exception =
            assertFailsWith<BadConfigurationException>(
                block = {
                    Earth.mars("")
                },
            )
        assertEquals(
            expected = "A bad configuration has been provided for Mission Details: `No Mars configuration`",
            actual = exception.message,
        )
    }

    @Test fun shouldExtractAMarsFromTheMissionDetails() {
        val mars = Earth.mars(missionDetails)
        assertEquals(expected = 6, actual = mars.width)
        assertEquals(expected = 4, actual = mars.height)
    }

    @Test fun shouldThrowBadConfigurationExceptionIfNoTextIsProvided() {
        val exception =
            assertFailsWith<BadConfigurationException>(
                block = {
                    Earth.missions("    ")
                },
            )
        assertEquals(expected = "A bad configuration has been provided for Missions: `No missions specified`", actual = exception.message)
    }

    @Test fun shouldThrowBadConfigurationExceptionIfNoMissionsAreProvided() {
        val exception =
            assertFailsWith<BadConfigurationException>(
                block = {
                    Earth.missions("1 2\n")
                },
            )
        assertEquals(expected = "A bad configuration has been provided for Missions: `No missions specified`", actual = exception.message)
    }

    @Test fun shouldReturnMissions() {
        val missions =
            Earth.missions(
                """
                1 1
                0 0 N
                RLF
                
                1 1 S
                FRF
                """.trimIndent(),
            )
        assertTrue { missions.size == 2 }
    }

    @Test
    fun shouldThrowBadConfigurationExceptionIfNoInputIsGiven() {
        val exception =
            assertFailsWith<BadConfigurationException>(
                block = {
                    Earth.missions("")
                },
            )
        assertEquals(
            expected = "A bad configuration has been provided for Missions: `No missions specified`",
            actual = exception.message,
        )
    }

    @Test
    fun shouldThrowBadConfigurationExceptionIfInputIsNotCorrectlyChunked() {
        val exception =
            assertFailsWith<BadConfigurationException>(
                block = {
                    Earth.missions(
                        """
                        1 1
                        RLF
                        """.trimIndent(),
                    )
                },
            )
        assertEquals(
            expected = "A bad configuration has been provided for Missions: `Input incorrectly chunked`",
            actual = exception.message,
        )
    }

    @Test
    fun shouldThrowBadConfigurationExceptionIfInputIsNotCorrectlyChunkedWithNoSpacing() {
        val exception =
            assertFailsWith<BadConfigurationException>(
                block = {
                    Earth.missions(
                        """
                        1 1
                        1 1 E
                        FRL
                        2 2 S
                        """.trimIndent(),
                    )
                },
            )
        assertEquals(
            expected = "A bad configuration has been provided for Missions: `Input incorrectly chunked`",
            actual = exception.message,
        )
    }

    @Test fun shouldExecuteSeveralNoOpExpeditions() {
        val mission1 = Mission.from(listOf("1 1 N", ""))
        val mission2 = Mission.from(listOf("2 2 E", ""))
        val mars = Mars.from("2 2")
        val log = Earth.expedition(mars = mars, missions = listOf(mission1, mission2))

        assertEquals(
            expected = 2,
            actual = log.outcomes.size,
        )

        assertTrue(Robot.startingWith(1, 1, Orientation.NORTH).equivalent(log.outcomes[0].robot))
        assertFalse(log.outcomes[0].lost)

        assertTrue(Robot.startingWith(2, 2, Orientation.EAST).equivalent(log.outcomes[1].robot))
        assertFalse(log.outcomes[1].lost)
    }
}
