package com.abalmas.glovodeliveryapi.auth.repository;

import com.abalmas.glovodeliveryapi.auth.entity.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on UserSecurity entities. This interface
 * provides methods for accessing and managing UserSecurity entities in the database.
 */
@Repository
public interface UserSecurityRepository extends JpaRepository<UserSecurity, Long> {

}
