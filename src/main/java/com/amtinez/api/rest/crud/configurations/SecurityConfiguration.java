package com.amtinez.api.rest.crud.configurations;

import com.amtinez.api.rest.crud.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

import static com.amtinez.api.rest.crud.constants.SecurityConstants.BCRYPT_PASSWORD_ENCODER_STRENGTH;
import static com.amtinez.api.rest.crud.constants.SecurityConstants.ROLE_ADMIN;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(BCRYPT_PASSWORD_ENCODER_STRENGTH);
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder authenticationManagerBuilder) {
        final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
        authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.antMatcher("/**")
            .authorizeRequests()
            .anyRequest()
            .hasAuthority(ROLE_ADMIN)
            .and()
            .csrf()
            .disable()
            .httpBasic();
    }

}
