package com.si.rkspringboot.service;

import com.si.rkspringboot.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;

public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        com.si.rkspringboot.entity.User user = userRepository.searchUserByEmail(email);
        UserBuilder builder = User.withUsername(user.getEmail())
                .password(user.getPassword());

        builder.roles(user.isAdmin() ? "ADMIN" : "USER");

        return builder.build();
    }
}
