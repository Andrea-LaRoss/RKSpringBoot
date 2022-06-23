package com.si.rkspringboot.service;

import com.si.rkspringboot.entity.Car;

import java.util.List;

public interface CarService {

    public List<Car> selAll();

    public List<Car> searchByBrand(String brand);

    public void delCar(Car car);

    public void insCar(Car car);

}
