package com.tinytrades.tinytradesbackend.security.config;

import com.tinytrades.tinytradesbackend.security.model.RoleType;
import com.tinytrades.tinytradesbackend.security.service.JwtAuthFilter;
import com.tinytrades.tinytradesbackend.security.service.SecurityUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity()
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final SecurityUserDetailsService securityUserDetailsService;
    private final JwtAuthFilter jwtAuthFilter;
    private final LogoutHandler logoutHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(securityUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(
                                    "/",
                                    "/api/v1/**",
                                    "/api/v1/auth/**",
                                    "/static/**",
                                    "/favicon.ico",
                                    "/index.html",
                                    "/asset-manifest.json",
                                    "/manifest.json",
                                    "/robots.txt",
                                    "/logo192.png",
                                    "/logo512.png",
                                    "/main",
                                    "/error/**")
                            .permitAll();

                    auth.requestMatchers(
                                    "/product/new")
                            .hasAnyRole(RoleType.ADMIN.toString(), RoleType.USER.toString());

                    auth.anyRequest().authenticated();
                });

        http.sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                    logout.logoutUrl("/api/v1/auth/logout")
                            .addLogoutHandler(logoutHandler)
                            .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                );

        return http.build();

    }

}
