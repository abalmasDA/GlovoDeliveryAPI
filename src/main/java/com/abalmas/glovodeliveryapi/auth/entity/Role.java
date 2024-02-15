package com.abalmas.glovodeliveryapi.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a role in the system. A role defines a set of permissions or privileges
 * granted to users.
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_role")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotEmpty
  @Max(100)
  @Column(nullable = false, unique = true)
  private String name;

  @NotEmpty
  private String description;
}
