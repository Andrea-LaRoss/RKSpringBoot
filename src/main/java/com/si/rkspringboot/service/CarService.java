package com.si.rkspringboot.service;

import com.si.rkspringboot.dto.CarDto;

import java.util.List;

public interface CarService {

    public List<CarDto> selAll();

    public CarDto checkNumPlate(String numPlate);

    public CarDto getCar(Long id);

    public void delCar(Long id);

    public void insCar(CarDto carDto);

}
