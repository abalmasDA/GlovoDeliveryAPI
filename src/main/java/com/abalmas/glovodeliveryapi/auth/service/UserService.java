package com.abalmas.glovodeliveryapi.auth.service;

import com.abalmas.glovodeliveryapi.auth.entity.User;
import com.abalmas.glovodeliveryapi.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for managing user entities in the system. This service provides methods for
 * creating, retrieving, updating, and deleting user entities.
 */
@Service
@RequiredArgsConstructor
public class UserService {

  /**
   * Repository for accessing user data in the database.
   */
  private final UserRepository userRepository;

  /**
   * Checks if a user with the given email exists in the system.
   *
   * @param email The email address of the user to check.
   * @return True if a user with the given email exists, false otherwise.
   */
  public boolean isUserExistByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  /**
   * Saves the user entity.
   *
   * @param user The user entity to save.
   * @return The saved user entity.
   */
  public User save(User user) {
    return userRepository.save(user);
  }
}
