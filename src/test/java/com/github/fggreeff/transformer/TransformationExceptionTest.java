package com.github.fggreeff.transformer;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test class for {@link TransformationException}
 */
class TransformationExceptionTest {

    private static final String MESSAGE = "aMessage";
    private static final Throwable CAUSE = new IOException();

    @Test
    public void TransformationException_shouldCreateExceptionWithMessage() {
        //when created with message
        TransformationException underTest = new TransformationException(MESSAGE);

        //then
        assertEquals(MESSAGE, underTest.getMessage());
        assertNull(underTest.getCause());
    }

    @Test
    public void TransformationException_shouldCreateExceptionWithMessageAndCause() {
        //when created with message and cause
        TransformationException underTest = new TransformationException(MESSAGE, CAUSE);

        //then
        assertEquals(MESSAGE, underTest.getMessage());
        assertEquals(CAUSE, underTest.getCause());
    }

}