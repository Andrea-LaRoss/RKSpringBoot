package com.si.rkspringboot.controller;

import com.si.rkspringboot.dto.UserDto;
import com.si.rkspringboot.entity.User;
import com.si.rkspringboot.service.UserService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> usersList = userService.selAll();
        if(usersList == null) {
            return new ResponseEntity<List<UserDto>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<UserDto>>(usersList, HttpStatus.OK);
        }
    }


    @GetMapping("/load/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id) {
        UserDto user = userService.getUser(id);
        if(user == null) {
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<UserDto>(user, HttpStatus.OK);
        }
    }


    @GetMapping("/check/{value}")
    public ResponseEntity<UserDto> checkEmail(@PathVariable("value") String email) {
        UserDto user = userService.checkEmail(email);
        if(user == null) {
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<UserDto>(user, HttpStatus.OK);
        }
    }


    @DeleteMapping("/remove/{id}")
    public void removeUser(@PathVariable("id") Long id) {
        userService.delUser(id);
    }


    @PostMapping("/add")
    public void addUser(@RequestBody User user) {
        userService.insUser(user);
    }

}
