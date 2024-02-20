package com.abalmas.glovodeliveryapi.auth.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    ErrorResponse errorResponse = new ErrorResponse("Invalid request body");
    return ResponseEntity.badRequest().body(errorResponse);
  }

  @ExceptionHandler(UserAlreadyExistException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ResponseEntity<Object> handleUserAlreadyExistException(
      UserAlreadyExistException ex) {
    ErrorResponse errorResponse = new ErrorResponse("User already exists");
    return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
  }
}

