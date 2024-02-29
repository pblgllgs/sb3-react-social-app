package com.pblgllgs.socialapp.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/*
 *
 * @author pblgl
 * Created on 29-02-2024
 *
 */
@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(
            UserAlreadyExistsException e,
            HttpServletRequest request
    ) {
        ErrorResponse ErrorResponse = new ErrorResponse(
                request.getRequestURI(),
                e.getClass().getSimpleName(),
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(ErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException e,
            HttpServletRequest request
    ) {
        ErrorResponse ErrorResponse = new ErrorResponse(
                request.getRequestURI(),
                e.getClass().getSimpleName(),
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(ErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OperationDeniedException.class)
    public ResponseEntity<ErrorResponse> handleOperationDeniedException(
            OperationDeniedException e,
            HttpServletRequest request
    ) {
        ErrorResponse ErrorResponse = new ErrorResponse(
                request.getRequestURI(),
                e.getClass().getSimpleName(),
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(ErrorResponse, HttpStatus.NOT_FOUND);
    }
}
