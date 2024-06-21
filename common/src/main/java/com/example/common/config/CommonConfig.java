package com.example.common.config;

import com.example.common.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

    @Bean
    public ResponseUtil responseUtil(ObjectMapper objectMapper) {
        return new ResponseUtil(objectMapper);
    }
}
