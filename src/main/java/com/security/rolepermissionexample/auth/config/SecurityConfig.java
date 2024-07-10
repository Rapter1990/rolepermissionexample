package com.security.rolepermissionexample.auth.config;

import com.security.rolepermissionexample.auth.filter.CustomBearerTokenAuthenticationFilter;
import com.security.rolepermissionexample.auth.security.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Security configuration class named {@link SecurityConfig} for setting up authentication and authorization in the application.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Defines the session authentication strategy to be used.
     *
     * @return a {@link SessionAuthenticationStrategy} for registering session authentication.
     */
    @Bean
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    /**
     * Configures the security filter chain for the application.
     *
     * @param httpSecurity the {@link HttpSecurity} object to configure.
     * @param customBearerTokenAuthenticationFilter the custom filter for bearer token authentication.
     * @param customAuthenticationEntryPoint the custom authentication entry point.
     * @return a {@link SecurityFilterChain} defining the security configuration.
     * @throws Exception if an error occurs while configuring security.
     */
    @Bean
    public SecurityFilterChain filterChain(
            final HttpSecurity httpSecurity,
            final CustomBearerTokenAuthenticationFilter customBearerTokenAuthenticationFilter,
            final CustomAuthenticationEntryPoint customAuthenticationEntryPoint
    ) throws Exception {

        httpSecurity
                .exceptionHandling(customizer -> customizer.authenticationEntryPoint(customAuthenticationEntryPoint))
                .cors(customizer -> customizer.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(customizer -> customizer
                        .requestMatchers(HttpMethod.POST, "/api/v1/authentication/**").permitAll()
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v2/api-docs/**",
                                "/v3/api-docs/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(customBearerTokenAuthenticationFilter, BearerTokenAuthenticationFilter.class);

        return httpSecurity.build();
    }

    /**
     * Configures CORS settings for the application.
     *
     * @return a {@link CorsConfigurationSource} defining the CORS configuration.
     */
    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Defines the password encoder to be used for encoding passwords.
     *
     * @return a {@link PasswordEncoder} using BCrypt hashing algorithm.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
