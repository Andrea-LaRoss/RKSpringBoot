package com.si.rkspringboot.repository;

import com.si.rkspringboot.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> searchByBrandContaining(String brand);

    List<Car> searchByModelContaining(String model);

    List<Car> searchByTypeContaining(String type);

    List<Car> searchByNumPlateContaining(String numPlate);

    List<Car> searchByRegDateContaining(String regDate);

    Car searchCarByNumPlate(String numPlate);

}
