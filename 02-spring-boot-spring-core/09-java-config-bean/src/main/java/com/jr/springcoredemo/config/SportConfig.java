package com.jr.springcoredemo.config;

import com.jr.springcoredemo.common.Coach;
import com.jr.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {
    @Bean
    // Bean uses a third-party class and expose it as a Spring Bean
    // @Bean("aquatic")
    // BeanId can be changed this way
    // BeanId defaults to the method_name
    public Coach swimCoach() {
        return new SwimCoach();
    }
}
