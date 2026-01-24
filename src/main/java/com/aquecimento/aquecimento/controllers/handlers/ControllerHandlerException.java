package com.aquecimento.aquecimento.controllers.handlers;

import com.aquecimento.aquecimento.controllers.errors.CustomError;
import com.aquecimento.aquecimento.controllers.errors.ValidationError;
import com.aquecimento.aquecimento.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
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
        ValidationError err = new ValidationError(Instant.now(), status.value(), "Erro inesperado. ", request.getRequestURI());

        for (FieldError f : e.getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(err);

    }

}
