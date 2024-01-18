package com.abalmas.glovodeliveryapi.utils.response;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


/**
 * The type Response handler.
 */
public class ResponseHandler {

  /**
   * Builds a response entity with the given message, HTTP status, and response object.
   *
   * @param message        the message to include in the response
   * @param httpStatus     the HTTP status code to set in the response
   * @param responseObject the object to include in the response
   * @return a ResponseEntity object containing the response
   */
  public static ResponseEntity<Object> responseBuilder(String message, HttpStatus httpStatus,
      Object responseObject) {
    Map<String, Object> response = new HashMap<>();
    response.put("message", message);
    response.put("httpStatus", httpStatus);
    response.put("data", responseObject);
    return new ResponseEntity<>(response, httpStatus);
  }

}
