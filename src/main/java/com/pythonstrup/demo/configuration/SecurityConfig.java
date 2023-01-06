package com.pythonstrup.demo.configuration;

import com.pythonstrup.demo.security.handler.CustomAuthenticationFailureHandler;
import com.pythonstrup.demo.security.handler.CustomAuthenticationProvider;
import com.pythonstrup.demo.security.handler.CustomAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// WebSecurityConfigurerAdapter가 deprecated됨.
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final CustomAuthenticationProvider customAuthenticationProvider;

    // WebSecurityConfigurerAdapter - configure(WebSecurity web) 역할
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring().requestMatchers("/swagger-ui/**", "/api-docs/**");
    }

    //WebSecurityConfigurerAdapter - configure(HttpSecurity http)의 역할
    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/v1/article/**").authenticated()
                .requestMatchers("/v1/auth/**").permitAll()
                .anyRequest().permitAll()
        );

        http.formLogin()
                .loginProcessingUrl("/v1/auth/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler);

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/v1/auth/logout"))
                .logoutSuccessUrl("/")
                .deleteCookies();

        http.sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true);

        return http.build();
    }

    // WebSecurityConfigurerAdapter - void configure(AuthenticationManagerBuilder auth)
    @Autowired
    public void registerProvider(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customAuthenticationProvider);
    }
}
