package com.demo.Auth_app_backend.config;

import com.demo.Auth_app_backend.dtos.ApiError;
import com.demo.Auth_app_backend.security.JwtAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf-> csrf.disable())
                .cors(Customizer.withDefaults())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeHttpRequest->
                authorizeHttpRequest
                        .requestMatchers("/api/v1/auth/register").permitAll()
                        .requestMatchers("/api/v1/auth/login").permitAll()
                        .anyRequest().authenticated()
        )
                .exceptionHandling(ex-> ex.authenticationEntryPoint((request, response, authException) -> {
                    //authException.printStackTrace();
                    response.setStatus(401);
                    response.setContentType("application/json");
                    String message = authException.getMessage();
                    String error = (String) request.getAttribute("error");
                    if (error != null) {
                        message = error;
                    }
//                    Map<String,String> errorMap = Map.of(
//                            "message",message,
//                            "statusCode", String.valueOf(401)
//                    );
                    //converting string into json
                    var apiError = ApiError.of(HttpStatus.UNAUTHORIZED.value(),"Unauthorized Access!!",message, request.getRequestURI(),true);
                    var objectMapper = new ObjectMapper();
                    response.getWriter().write(objectMapper.writeValueAsString(apiError));
                }))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration  configuration) {
        return configuration.getAuthenticationManager();
    }

    //Managing In memory User
//    @Bean
//    public UserDetailsService users() {
//        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
//        UserDetails user1 = userBuilder.username("Amir").password("abx").roles("ADMIN").build();
//        UserDetails user2 = userBuilder.username("Salman").password("abx").roles("ADMIN").build();
//        UserDetails user3 = userBuilder.username("Shah Rukh").password("abx").roles("USER").build();
//        return new InMemoryUserDetailsManager(user1,user2,user3);
//    }
}
