package com.si.rkspringboot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.si.rkspringboot.entity.User;
import com.si.rkspringboot.repository.UserRepository;
import com.si.rkspringboot.security.JwtProvider;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Map<String, Object> login(String email, String password) {
        User user = this.userRepository.searchUserByEmail(email);
        if (!this.passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials");
        }
        Map<String, Object> claimMap = new HashMap<>(0);
        claimMap.put("role", user.isAdmin()? "ADMIN" : "USER");
        String jwt =  JwtProvider.createJwt(email, claimMap);
        claimMap.put("token", jwt);
        return claimMap;
    }
}
