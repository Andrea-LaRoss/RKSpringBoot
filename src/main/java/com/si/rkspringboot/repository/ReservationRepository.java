package com.si.rkspringboot.repository;

import com.si.rkspringboot.entity.Car;
import com.si.rkspringboot.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Car> searchByCarBetween(LocalDate startDate, LocalDate endDate);

}
