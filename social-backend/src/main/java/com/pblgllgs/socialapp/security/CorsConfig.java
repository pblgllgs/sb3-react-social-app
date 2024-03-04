package com.pblgllgs.socialapp.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;

/*
 *
 * @author pblgl
 * Created on 04-03-2024
 *
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsConfiguration corsConfiguration(HttpServletRequest request){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(
                Arrays.asList(
                        "http://localhost:3000",
                        "http://localhost:4000",
                        "http://localhost:5173"
                )
        );
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setMaxAge(3600L);
        return config;
    }
}
