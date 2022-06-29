package com.si.rkspringboot.security;

import com.si.rkspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    public static final String[] ADMIN_URL_MATCHER = {
            "/api/cars/remove/**",
            "/api/cars/add",

            "/api/users/add",
            "/api/users/remove/**",
            "/api/users",
    };

    public static final String[] COMMON_URL_MATCHER = {
            "/api/cars",
            "/api/reservations",
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new AuthorizationFilter(authenticationManager(), this.userRepository), UsernamePasswordAuthenticationFilter.class).authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(ADMIN_URL_MATCHER).hasAnyRole("ADMIN")
                .antMatchers(COMMON_URL_MATCHER).hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
