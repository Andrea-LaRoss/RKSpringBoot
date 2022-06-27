package com.si.rkspringboot.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        User.UserBuilder users = User.builder();

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(users.username("Andrea")
                .password(new BCryptPasswordEncoder().encode("1234"))
                .roles("USER")
                .build());

        manager.createUser(users.username("AdminTest")
                .password(new BCryptPasswordEncoder().encode("1234"))
                .roles("USER", "ADMIN")
                .build());

        return manager;

    }

    private static final String[] USER_MATCHER = {"/api/reservation/**"};
    private static final String[] ADMIN_MATCHER = {"/api/**"};

}
