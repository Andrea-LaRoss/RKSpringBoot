package com.si.rkspringboot.dto;

import com.si.rkspringboot.entity.Reservation;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class CarDto {

    private Long id;
    private String brand;
    private String model;
    private String type;
    private String numPlate;
    private LocalDate regDate;

    //private Set<Reservation> reservation = new HashSet<>();

}
