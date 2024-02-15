package com.abalmas.glovodeliveryapi.auth.service;

import com.abalmas.glovodeliveryapi.auth.entity.Role;
import com.abalmas.glovodeliveryapi.auth.exception.RoleNotFoundException;
import com.abalmas.glovodeliveryapi.auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for managing roles in the system. This service provides methods for creating,
 * retrieving, updating, and deleting roles.
 */
@Service
@RequiredArgsConstructor
public class RoleService {

  /**
   * Repository for accessing role data in the database.
   */
  private final RoleRepository roleRepository;

  /**
   * Retrieves a role by its name.
   *
   * @param name The name of the role to retrieve.
   * @return The role entity with the specified name, or null if not found.
   */
  public Role getRoleByName(String name) {
    return roleRepository.findByName(name)
        .orElseThrow(() -> new RoleNotFoundException("Role " + name + " is not found!"));
  }
}
