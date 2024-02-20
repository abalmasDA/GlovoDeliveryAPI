package com.abalmas.glovodeliveryapi.auth.service;

import com.abalmas.glovodeliveryapi.auth.dto.SignUpDto;
import com.abalmas.glovodeliveryapi.auth.entity.User;
import com.abalmas.glovodeliveryapi.auth.entity.UserSecurity;
import com.abalmas.glovodeliveryapi.auth.exception.UserAlreadyExistException;
import com.abalmas.glovodeliveryapi.auth.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for user registration logic. This service handles the registration
 * process, including validation and database operations.
 */
@Service
@RequiredArgsConstructor
public class RegistrationService {

  /**
   * Service responsible for user management operations.
   */
  private final UserService userService;

  /**
   * Service responsible for role management operations.
   */
  private final RoleService roleService;

  /**
   * Service responsible for user security management operations.
   */
  private final UserSecurityService userSecurityService;

  private final UserMapper userMapper;

  /**
   * Checks if a user with the given email address exists.
   *
   * @param email The email address to check for existence.
   * @return True if a user with the specified email address exists, false otherwise.
   */
  public boolean isUserExistByEmail(String email) {
    return userService.isUserExistByEmail(email);
  }

  /**
   * Method for registering a new user. This method validates the sign-up request, creates a new
   * user entity, and persists it to the database.
   *
   * @param signUpDto DTO containing user sign-up data.
   * @return The registered User object.
   */
  @Transactional
  public User registerUser(SignUpDto signUpDto) {
    if (isUserExistByEmail(signUpDto.getEmail())) {
      throw new UserAlreadyExistException("User is already registered!");
    }

    User user = userService.save(userMapper.toEntity(signUpDto));

    UserSecurity userSecurity = UserSecurity.builder()
        .password(signUpDto.getPassword())
        .userId(user.getId())
        .userRoleId(roleService.getRoleByName("ROLE_USER").getId())
        .build();
    userSecurityService.save(userSecurity);

    return user;
  }
}
