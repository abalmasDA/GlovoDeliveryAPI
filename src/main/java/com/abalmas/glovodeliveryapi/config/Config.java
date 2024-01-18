package com.abalmas.glovodeliveryapi.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Returns a new instance of ModelMapper.
 */
@Configuration
public class Config {

  /**
   * Returns a new instance of ModelMapper.
   *
   * @return the ModelMapper instance
   */
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }


}
