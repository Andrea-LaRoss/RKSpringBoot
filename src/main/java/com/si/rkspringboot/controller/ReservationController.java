package com.si.rkspringboot.controller;

import com.si.rkspringboot.dto.CarDto;
import com.si.rkspringboot.dto.ReservationDto;
import com.si.rkspringboot.dto.UserDto;
import com.si.rkspringboot.entity.Reservation;
import com.si.rkspringboot.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;


    @GetMapping()
    public ResponseEntity<List<ReservationDto>> getReservations() {
        List<ReservationDto> reservationsList = reservationService.selAll();
        if(reservationsList == null) {
            return new ResponseEntity<List<ReservationDto>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<ReservationDto>>(reservationsList, HttpStatus.OK);
        }
    }


    @GetMapping("/remove/{id}")
    public void deleteReservations(@PathVariable("id") Long id) {
        Reservation reservation = reservationService.getReservation(id);
        reservationService.delReservation(reservation);
    }


    @GetMapping("/add")
    public void addReservation() {
        Reservation reservation = new Reservation();

        reservationService.insReservation(reservation);
    }

}
