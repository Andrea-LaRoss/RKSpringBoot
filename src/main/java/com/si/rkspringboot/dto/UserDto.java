package com.si.rkspringboot.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {

    private long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private boolean admin;

}
