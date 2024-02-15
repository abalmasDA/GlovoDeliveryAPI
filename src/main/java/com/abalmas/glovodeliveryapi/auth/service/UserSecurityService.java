package com.abalmas.glovodeliveryapi.auth.service;

import com.abalmas.glovodeliveryapi.auth.entity.UserSecurity;
import com.abalmas.glovodeliveryapi.auth.repository.UserSecurityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service layer component responsible for managing user security-related operations.
 */
@Service
@RequiredArgsConstructor
public class UserSecurityService {

  private final UserSecurityRepository userSecurityRepository;

  /**
   * Saves user security data.
   *
   * @param userSecurity The user security data to be saved.
   * @return True if the user security data is successfully saved, false otherwise.
   */
  public UserSecurity save(UserSecurity userSecurity) {
    return userSecurityRepository.save(userSecurity);
  }
}
