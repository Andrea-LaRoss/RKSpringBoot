package com.si.rkspringboot.controller;

import com.si.rkspringboot.dto.CarDto;
import com.si.rkspringboot.dto.ReservationDto;
import com.si.rkspringboot.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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


    @GetMapping("/load/{id}")
    public ResponseEntity<ReservationDto> getReservation(@PathVariable("id") Long id) {
        ReservationDto reservationDto = reservationService.getReservation(id);
        if(reservationDto == null) {
            return new ResponseEntity<ReservationDto>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<ReservationDto>(reservationDto, HttpStatus.OK);
        }
    }


    @GetMapping("/available")
    public ResponseEntity<List<CarDto>> getAvailableCars(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<CarDto> carsList = reservationService.availableCars(startDate, endDate);
        if(carsList == null) {
            return new ResponseEntity<List<CarDto>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<CarDto>>(carsList, HttpStatus.OK);
        }
    }


    @DeleteMapping("/remove/{id}")
    public void deleteReservations(@PathVariable("id") Long id) {
        reservationService.delReservation(id);
    }


    @RequestMapping(value = "/add", method = { RequestMethod.POST, RequestMethod.PUT })
    public void addReservation(@RequestBody ReservationDto reservationDto) {
        reservationService.insReservation(reservationDto);
    }

}
