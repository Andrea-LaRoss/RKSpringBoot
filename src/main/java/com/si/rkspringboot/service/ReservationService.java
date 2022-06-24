package com.si.rkspringboot.service;

import com.si.rkspringboot.dto.ReservationDto;
import com.si.rkspringboot.entity.Reservation;

import java.util.List;

public interface ReservationService {

    public List<ReservationDto> selAll();

    public  Reservation getReservation(Long id);

    public void delReservation(Reservation reservation);

    public void insReservation(Reservation reservation);

}
