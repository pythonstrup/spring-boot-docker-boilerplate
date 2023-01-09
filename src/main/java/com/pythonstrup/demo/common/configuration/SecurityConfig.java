package com.pythonstrup.demo.common.configuration;

import com.pythonstrup.demo.domain.auth.handler.CustomAuthenticationFailureHandler;
import com.pythonstrup.demo.domain.auth.handler.CustomAuthenticationManager;
import com.pythonstrup.demo.domain.auth.handler.CustomAuthenticationSuccessHandler;
import com.pythonstrup.demo.domain.auth.handler.JsonUsernamePasswordAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// WebSecurityConfigurerAdapter가 deprecated됨.
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final CustomAuthenticationManager customAuthenticationManager;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    // WebSecurityConfigurerAdapter - configure(WebSecurity web) 역할
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring().requestMatchers("/swagger-ui/**", "/api-docs/**");
    }

    //WebSecurityConfigurerAdapter - configure(HttpSecurity http)의 역할
    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = authenticationManager(http.getSharedObject(AuthenticationConfiguration.class));

        http.csrf().disable();

        http.formLogin().disable();

        http.addFilterBefore(
                authenticationFilter(authenticationManager),
                UsernamePasswordAuthenticationFilter.class);

        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/v1/article/**").authenticated()
                .anyRequest().permitAll()
        );

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/v1/auth/logout"))
                .logoutSuccessUrl("/")
                .deleteCookies();

        http.sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //Filter 등록을 위한 Bean
    @Bean
    public JsonUsernamePasswordAuthenticationFilter authenticationFilter(AuthenticationManager authenticationManager) {
        JsonUsernamePasswordAuthenticationFilter authenticationFilter = new JsonUsernamePasswordAuthenticationFilter(customAuthenticationSuccessHandler,
                customAuthenticationFailureHandler, customAuthenticationManager);
        authenticationFilter.setAuthenticationManager(authenticationManager);

        SecurityContextRepository contextRepository = new HttpSessionSecurityContextRepository();
        authenticationFilter.setSecurityContextRepository(contextRepository);

        return authenticationFilter;
    }
}
