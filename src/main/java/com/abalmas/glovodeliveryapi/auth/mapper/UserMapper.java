package com.abalmas.glovodeliveryapi.auth.mapper;

import com.abalmas.glovodeliveryapi.auth.dto.SignUpDto;
import com.abalmas.glovodeliveryapi.auth.entity.User;
import org.mapstruct.Mapper;

/**
 * Interface for mapping SignUpDto to User entities.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

  User toEntity(SignUpDto signUpDto);
}
