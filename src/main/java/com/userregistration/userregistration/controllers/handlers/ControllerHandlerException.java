package com.userregistration.userregistration.controllers.handlers;

import com.userregistration.userregistration.controllers.errors.CustomError;
import com.userregistration.userregistration.controllers.errors.ValidationError;
import com.userregistration.userregistration.services.exceptions.BusinessException;
import com.userregistration.userregistration.services.exceptions.DatabaseException;
import com.userregistration.userregistration.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerHandlerException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> MethodArgumentNotValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ValidationError err = new ValidationError(Instant.now(), status.value(), "VALIDATION ERROR", request.getRequestURI());

        for (FieldError f : e.getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
        }

        for (ObjectError g : e.getBindingResult().getGlobalErrors()) {
            err.addError("global", g.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(err);

    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<CustomError> businessConflict(BusinessException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);

    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomError> databaseErr(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

}
