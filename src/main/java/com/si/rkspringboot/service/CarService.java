package com.si.rkspringboot.service;

import com.si.rkspringboot.dto.CarDto;
import com.si.rkspringboot.entity.Car;

import java.time.LocalDate;
import java.util.List;

public interface CarService {

    public List<CarDto> selAll();

    public CarDto checkNumPlate(String numPlate);

    public Car getCar(Long id);

    public void delCar(Car car);

    public void insCar(Car car);

}
