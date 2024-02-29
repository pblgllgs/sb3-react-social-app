package com.pblgllgs.socialapp.exception;
/*
 *
 * @author pblgl
 * Created on 18-02-2024
 *
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class OperationDeniedException extends RuntimeException{

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public OperationDeniedException(String message) {
        super(message);
    }
}
