package com.si.rkspringboot.service;

import com.si.rkspringboot.entity.Car;
import com.si.rkspringboot.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> selAll() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> searchByBrand(String brand) {
        return carRepository.searchAllByBrand(brand);
    }

    @Override
    public void delCar(Car car) {
        carRepository.delete(car);
    }

    @Override
    public void insCar(Car car) {
        carRepository.save(car);
    }

}
