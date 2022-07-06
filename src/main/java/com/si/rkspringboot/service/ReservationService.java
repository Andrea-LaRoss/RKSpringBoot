package com.si.rkspringboot.service;

import com.si.rkspringboot.dto.CarDto;
import com.si.rkspringboot.dto.ReservationDto;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    public List<ReservationDto> selAll();

    public ReservationDto getReservation(Long id);

    public List<CarDto> availableCars(LocalDate startDate, LocalDate endDate);

    public void delReservation(Long id);

    public void insReservation(ReservationDto reservationDto);

}
