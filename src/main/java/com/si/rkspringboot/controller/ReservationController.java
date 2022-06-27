package com.si.rkspringboot.controller;

import com.si.rkspringboot.dto.CarDto;
import com.si.rkspringboot.dto.ReservationDto;
import com.si.rkspringboot.dto.UserDto;
import com.si.rkspringboot.entity.Car;
import com.si.rkspringboot.entity.Reservation;
import com.si.rkspringboot.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping()
    public ResponseEntity<List<ReservationDto>> getReservations() {
        List<ReservationDto> reservationsList = reservationService.selAll();
        if(reservationsList == null) {
            return new ResponseEntity<List<ReservationDto>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<ReservationDto>>(reservationsList, HttpStatus.OK);
        }
    }


    @GetMapping("/available")
    public ResponseEntity<List<Car>> getAvailableCars(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<ReservationDto> reservationList = reservationService.availableCars(startDate, endDate);
        List<Car> carsList = new ArrayList<>();
        reservationList.forEach(r -> carsList.add(r.getCar()));
        if(carsList == null) {
            return new ResponseEntity<List<Car>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<Car>>(carsList, HttpStatus.OK);
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


    private List<CarDto> convertToDtoCars(List<Car> carsList) {
        List<CarDto> carsDto = new ArrayList<>();
        if (carsList != null) {
            carsDto = carsList
                    .stream()
                    .map(source -> modelMapper.map(source, CarDto.class))
                    .collect(Collectors.toList());
        }
        return carsDto;
    }

}
