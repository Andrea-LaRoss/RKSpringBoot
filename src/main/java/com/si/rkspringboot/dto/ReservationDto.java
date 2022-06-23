package com.si.rkspringboot.dto;

import com.si.rkspringboot.entity.Car;
import com.si.rkspringboot.entity.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationDto {

    private long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    private User user;
    private Car car;

}
