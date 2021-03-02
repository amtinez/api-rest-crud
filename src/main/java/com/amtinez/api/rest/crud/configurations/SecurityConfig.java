package com.amtinez.api.rest.crud.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

import static com.amtinez.api.rest.crud.constants.SecurityConstants.BCRYPT_PASSWORD_ENCODER_STRENGTH;
import static com.amtinez.api.rest.crud.constants.SecurityConstants.ROLE_ADMIN;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(BCRYPT_PASSWORD_ENCODER_STRENGTH);
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder authenticationManagerBuilder) {
        final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
        authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.antMatcher("/**")
            .authorizeRequests()
            .anyRequest()
            .hasRole(ROLE_ADMIN)
            .and()
            .csrf()
            .disable()
            .httpBasic();
    }

}
