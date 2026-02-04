package com.guilherme.adopted.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> requestValidationError(MethodArgumentNotValidException ex, HttpServletRequest request){

        List<ErrorField> errors = new ArrayList<>();
        
        ex.getBindingResult().getFieldErrors()
        .forEach(error -> 
            errors.add(new ErrorField(error.getDefaultMessage(), error.getField()))
        );

        ApiError apiError = new ApiError(
            400,
            "invalid information provided",
            request.getRequestURI(),
            errors
        );
 
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(DuplicateDateException.class)
    public ResponseEntity<ApiError> duplicatedDataError(DuplicateDateException ex, HttpServletRequest request){
        ApiError apiError = new ApiError(
            400,
            ex.getMessage(),
            request.getRequestURI(),
            null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiError> usernameNotFoundError(UsernameNotFoundException ex, HttpServletRequest request){
        ApiError apiError = new ApiError(
            401,
            ex.getMessage(),
            request.getRequestURI(),
            null
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }

    @ExceptionHandler(NoInformationFoundException.class)
    public ResponseEntity<ApiError> noInformationFoundError(NoInformationFoundException ex, HttpServletRequest request){
        ApiError apiError = new ApiError(
            400,
            ex.getMessage(),
            request.getRequestURI(),
            null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    } 
}
