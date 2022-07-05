package com.si.rkspringboot.controller;

import com.si.rkspringboot.dto.AuthenticationDto;
import com.si.rkspringboot.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public Map<String, Object> login(@RequestBody AuthenticationDto authDto) {
        return this.authenticationService.login(authDto.getEmail(), authDto.getPassword());
    }
}
