package com.pblgllgs.socialapp.exception;

import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class GlobalHandlerExceptions {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(
            UserAlreadyExistsException e,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                request.getRequestURI(),
                e.getClass().getSimpleName(),
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException e,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                request.getRequestURI(),
                e.getClass().getSimpleName(),
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OperationDeniedException.class)
    public ResponseEntity<ErrorResponse> handleOperationDeniedException(
            OperationDeniedException e,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                request.getRequestURI(),
                e.getClass().getSimpleName(),
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(
            InvalidCredentialsException e,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                request.getRequestURI(),
                e.getClass().getSimpleName(),
                e.getMessage(),
                HttpStatus.UNAUTHORIZED.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidJwtTokenException.class)
    public ResponseEntity<ErrorResponse> handleInvalidJwtTokenException(
            InvalidJwtTokenException e,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                request.getRequestURI(),
                e.getClass().getSimpleName(),
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ErrorResponse> handleInvalidJwtTokenException(
            MalformedJwtException e,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                request.getRequestURI(),
                e.getClass().getSimpleName(),
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleInvalidJwtTokenException(
            UsernameNotFoundException e,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                request.getRequestURI(),
                e.getClass().getSimpleName(),
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(
            BadCredentialsException e,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                request.getRequestURI(),
                e.getClass().getSimpleName(),
                e.getMessage(),
                HttpStatus.FORBIDDEN.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
}
