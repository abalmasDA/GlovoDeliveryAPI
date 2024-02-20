package com.abalmas.glovodeliveryapi.auth.repository;

import com.abalmas.glovodeliveryapi.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository interface for performing CRUD operations on User entities. This interface provides
 * methods for accessing and managing User entities in the database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByEmail(String email);
}

