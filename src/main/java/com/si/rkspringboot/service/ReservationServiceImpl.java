package com.si.rkspringboot.service;

import com.si.rkspringboot.dto.CarDto;
import com.si.rkspringboot.dto.ReservationDto;
import com.si.rkspringboot.entity.Car;
import com.si.rkspringboot.entity.Reservation;
import com.si.rkspringboot.repository.CarRepository;
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
    private CarRepository carRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<ReservationDto> selAll() {
        List<Reservation> reservationList = reservationRepository.findAll();
        return convertToDtoList(reservationList);
    }


    @Override
    public List<CarDto> availableCars(LocalDate startDate, LocalDate endDate) {
        List<Reservation> reservationsList = reservationRepository.searchAllByStartDateBetweenOrEndDateBetween(startDate, endDate, startDate, endDate);
        List<Car> carsList = carRepository.findAll();
        reservationsList.forEach(r -> carsList.remove(r.getCar()));
        return convertToDtoCars(carsList);
    }


    @Override
    public ReservationDto getReservation(Long id) {
        Reservation reservation = reservationRepository.getReferenceById(id);
        return this.convertToDto(reservation);
    }


    @Override
    public void delReservation(Long id) { reservationRepository.deleteById(id); }


    @Override
    public void insReservation(ReservationDto reservationDto) {
        Reservation reservation = convertToReservation(reservationDto);
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


    private ReservationDto convertToDto(Reservation reservation) {
        ReservationDto reservationDto = null;
        if (reservation != null) {
            reservationDto =  modelMapper.map(reservation, ReservationDto.class);
        }
        return reservationDto;
    }


    private Reservation convertToReservation(ReservationDto reservationDto) {
        Reservation reservation = null;
        if (reservationDto != null) {
            reservation =  modelMapper.map(reservationDto, Reservation.class);
        }
        return reservation;
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
