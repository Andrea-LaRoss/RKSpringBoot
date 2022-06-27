package com.si.rkspringboot.service;

import com.si.rkspringboot.dto.CarDto;
import com.si.rkspringboot.entity.Car;

import java.time.LocalDate;
import java.util.List;

public interface CarService {

    public List<CarDto> selAll();

    public List<CarDto> searchByBrand(String brand);

    public List<CarDto> searchByModel(String model);

    public List<CarDto> searchByType(String type);

    public List<CarDto> searchByNumPlate(String numPlate);

    public List<CarDto> searchByRegDate(LocalDate regDate);

    public CarDto checkNumPlate(String numPlate);

    public Car getCar(Long id);

    public void delCar(Car car);

    public void insCar(Car car);

}
