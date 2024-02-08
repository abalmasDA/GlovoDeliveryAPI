package com.abalmas.glovodeliveryapi.utils.exception;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


/**
 * The type Error response.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class ErrorResponse {

  private LocalDateTime timestamp;
  private int status;
  private String error;
  private String message;
  private Map<String, String> errors;

}
