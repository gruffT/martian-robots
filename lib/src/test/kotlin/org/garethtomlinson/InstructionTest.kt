package org.garethtomlinson

import org.garethtomlinson.exceptions.BadConfigurationException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class InstructionTest {
    @Test fun shouldReturnForwardForF() {
        assertEquals(expected = Instruction.FORWARD, actual = Instruction.from("F"))
    }

    @Test fun shouldReturnLeftForL() {
        assertEquals(expected = Instruction.LEFT, actual = Instruction.from("L"))
    }

    @Test fun shouldReturnRightForR() {
        assertEquals(expected = Instruction.RIGHT, actual = Instruction.from("R"))
    }

    @Test fun shouldThrowBadConfigurationExceptionForUnknownInput() {
        val exception = assertFailsWith<BadConfigurationException> { Instruction.from("?") }
        assertEquals(expected = "A bad configuration has been provided for Instruction: `?`", actual = exception.message)
    }
}
