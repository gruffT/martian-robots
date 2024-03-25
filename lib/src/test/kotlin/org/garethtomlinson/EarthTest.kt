package org.garethtomlinson

import org.garethtomlinson.exceptions.BadConfigurationException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

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
}
