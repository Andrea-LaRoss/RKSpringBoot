package com.si.rkspringboot.service;

import com.si.rkspringboot.dto.CarDto;
import com.si.rkspringboot.dto.ReservationDto;
import com.si.rkspringboot.entity.Car;
import com.si.rkspringboot.entity.Reservation;
import com.si.rkspringboot.repository.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<ReservationDto> selAll() {
        List<Reservation> reservationList = reservationRepository.findAll();
        return convertToDtoList(reservationList);
    }


    @Override
    public List<ReservationDto> availableCars(LocalDate startDate, LocalDate endDate) {
        List<Reservation> carsList = reservationRepository.searchByStartDateBetween(startDate, endDate);
        return convertToDtoList(carsList);
    }

    @Override
    public Reservation getReservation(Long id) {
        return reservationRepository.getReferenceById(id);
    }

    @Override
    public void delReservation(Reservation reservation) {
        reservationRepository.delete(reservation);
    }

    @Override
    public void insReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }


    private List<ReservationDto> convertToDtoList(List<Reservation> reservationsList) {
        List<ReservationDto> reservationsDto = new ArrayList<>();
        if (reservationsList != null) {
            reservationsDto = reservationsList
                    .stream()
                    .map(source -> modelMapper.map(source, ReservationDto.class))
                    .collect(Collectors.toList());
        }
        return reservationsDto;
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
