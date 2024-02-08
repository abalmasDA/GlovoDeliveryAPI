package com.abalmas.glovodeliveryapi.utils.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * The type Exception handler.
 */
@RestControllerAdvice
@AllArgsConstructor
public class ExceptionHandler {

  private ErrorResponse errorResponse;

  /**
   * Handles MethodArgumentNotValidException and returns a ResponseEntity{@code Object}.
   *
   * @param methodArgumentNotValidException the MethodArgumentNotValidException to be handled
   * @return the ResponseEntity{@code Object} containing the error response
   */
  @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleValidationException(
      MethodArgumentNotValidException methodArgumentNotValidException) {

    errorResponse = ErrorResponse.builder()
        .timestamp(LocalDateTime.now())
        .status(HttpStatus.BAD_REQUEST.value())
        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
        .message("Validation error")
        .errors(methodArgumentNotValidException.getBindingResult().getFieldErrors().stream()
            .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)))
        .build();

    return ResponseEntity.badRequest().body(errorResponse);
  }

  /**
   * Handles the exception when an order is not found.
   *
   * @param ex the exception that was thrown
   * @return the response entity with the error details
   */
  @org.springframework.web.bind.annotation.ExceptionHandler(OrderNotFoundException.class)
  public ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException ex) {
    errorResponse = ErrorResponse.builder()
        .timestamp(LocalDateTime.now())
        .status(HttpStatus.NOT_FOUND.value())
        .error(HttpStatus.NOT_FOUND.getReasonPhrase())
        .message("Order not found")
        .build();

    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(errorResponse);
  }

}
