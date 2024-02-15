package com.abalmas.glovodeliveryapi.auth.controller;

import com.abalmas.glovodeliveryapi.auth.dto.SignUpDto;
import com.abalmas.glovodeliveryapi.auth.entity.User;
import com.abalmas.glovodeliveryapi.auth.service.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class responsible for handling registration-related requests. This controller provides
 * endpoints for user registration.
 */
@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class RegistrationController {

  private final RegistrationService registrationService;

  /**
   * Endpoint for user registration. Accepts POST requests with user details and registers a new
   * user.
   *
   * @param signUpDto DTO containing new user's details such as username, password, etc.
   * @return The registered User entity if successful, or null if the registration fails.
   */
  @Operation(summary = "Register user",
      description = "This can only be done by the logged in user.", tags = {"Registration"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User successfully registered",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = User.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid request body",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = String.class))}),
      @ApiResponse(responseCode = "409", description = "User already exists",
          content = {
              @Content(mediaType = "application/json",
                  schema = @Schema(implementation = String.class))})})
  @PostMapping
  public User registerUser(@RequestBody @Valid SignUpDto signUpDto) {
    return registrationService.registerUser(signUpDto);
  }
}