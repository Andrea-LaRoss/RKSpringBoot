package com.si.rkspringboot.repository;

import com.si.rkspringboot.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    Car searchCarByNumPlate(String numPlate);

}
