package com.github.fggreeff.transformer;

/**
 * Exception thrown when there is an error during transformation.
 *
 * @see Transformer
 */
public class TransformationException extends RuntimeException {

    /**
     * Exception with message
     */
    public TransformationException(final String message) {
        super(message);
    }

    /**
     * Exception with message and cause
     */
    public TransformationException(final String message, final Throwable cause) {
        super(message, cause);

    }
}
