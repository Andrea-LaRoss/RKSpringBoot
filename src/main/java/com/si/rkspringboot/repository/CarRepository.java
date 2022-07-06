package com.si.rkspringboot.repository;

import com.si.rkspringboot.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

    Car searchCarByNumPlate(String numPlate);

}
