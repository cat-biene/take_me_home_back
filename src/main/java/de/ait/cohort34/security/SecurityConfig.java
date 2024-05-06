package de.ait.cohort34.security;

import ait.cohort34.accounting.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    final CustomWebSecurity webSecurity;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(
                        autz->autz
                                .requestMatchers("/account/register").hasRole((Role.USER.name()))
                                .requestMatchers("/pet/found/**").permitAll()
                                .requestMatchers("/api/auth").permitAll()
                                .requestMatchers("/account/user/{login}/role").hasRole(Role.ADMINISTRATOR.name())
                                .requestMatchers(HttpMethod.GET,"/account/users").hasRole(Role.ADMINISTRATOR.name())//Почему только админ может получить User;
                                .requestMatchers(HttpMethod.PUT,"/account/user/{login}").hasRole((Role.USER.name()))
                                .requestMatchers(HttpMethod.DELETE,"/account/user/{login}").hasRole(Role.ADMINISTRATOR.name())
                                .requestMatchers(HttpMethod.POST, "/pet/add/{author}").hasAnyRole((Role.USER.name()),(Role.ADMINISTRATOR.name()))
                                .requestMatchers(HttpMethod.PUT,"/pet/update/{id}").hasRole((Role.USER.name()))
                                .requestMatchers(HttpMethod.DELETE, "/pet/{caption}").hasAnyRole((Role.USER.name()),(Role.ADMINISTRATOR.name()))
                                .anyRequest().authenticated())
                                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new BasicAuthFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class))), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
