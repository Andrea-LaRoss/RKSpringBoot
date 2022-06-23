package com.si.rkspringboot.dto;

import com.si.rkspringboot.entity.Reservation;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {

    private long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private boolean admin;

    private Set<Reservation> reservations = new HashSet<>();

}
