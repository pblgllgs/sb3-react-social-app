package com.pblgllgs.socialapp.exception;
/*
 *
 * @author pblgl
 * Created on 01-03-2024
 *
 */

public class InvalidCredentialsException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
