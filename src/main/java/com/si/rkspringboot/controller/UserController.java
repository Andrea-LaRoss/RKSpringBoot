package com.si.rkspringboot.controller;

import com.si.rkspringboot.dto.UserDto;
import com.si.rkspringboot.entity.User;
import com.si.rkspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @GetMapping("/email/{value}")
    public ResponseEntity<List<UserDto>> searchEmail(@PathVariable("value") String email) {
        List<UserDto> usersList = userService.searchByEmail(email);
        if(usersList == null) {
            return new ResponseEntity<List<UserDto>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<UserDto>>(usersList, HttpStatus.OK);
        }
    }


    @GetMapping("/firstname/{value}")
    public ResponseEntity<List<UserDto>> searchFirstName(@PathVariable("value") String firstName) {
        List<UserDto> usersList = userService.searchByFirstName(firstName);
        if(usersList == null) {
            return new ResponseEntity<List<UserDto>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<UserDto>>(usersList, HttpStatus.OK);
        }
    }


    @GetMapping("/lastname/{value}")
    public ResponseEntity<List<UserDto>> searchLastName(@PathVariable("value") String lastName) {
        List<UserDto> usersList = userService.searchByLastName(lastName);
        if(usersList == null) {
            return new ResponseEntity<List<UserDto>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<UserDto>>(usersList, HttpStatus.OK);
        }
    }


    @GetMapping("/birthday/{value}")
    public ResponseEntity<List<UserDto>> searchBirthday(@PathVariable("value") LocalDate birthday) {
        List<UserDto> usersList = userService.searchByBirthday(birthday);
        if(usersList == null) {
            return new ResponseEntity<List<UserDto>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<UserDto>>(usersList, HttpStatus.OK);
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


    @GetMapping("/remove/{id}")
    public void removeCar(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        userService.delUser(user);
    }


    @GetMapping("/add")
    public void addCar() {
        User user = new User();
        user.setEmail("Test");
        user.setFirstName("Test");
        user.setLastName("Test");
        userService.insUser(user);
    }

}
