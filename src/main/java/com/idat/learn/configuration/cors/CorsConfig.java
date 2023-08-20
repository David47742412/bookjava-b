package com.idat.learn.configuration.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Configura las rutas que quieras permitir (/** para todas)
                        .allowedOrigins("http://localhost:3001")  // Dominio permitido para las solicitudes
                        .allowedMethods("GET", "POST", "PUT", "DELETE")   // Métodos HTTP permitidos
                        .allowedHeaders("*")                              // Cabeceras permitidas
                        .allowCredentials(true)                           // Permitir el envío de credenciales (cookies, autenticación, etc.)
                        .maxAge(3600);
            }
        };
    }

}
