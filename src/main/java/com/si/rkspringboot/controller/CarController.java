package com.si.rkspringboot.controller;

import com.si.rkspringboot.dto.CarDto;
import com.si.rkspringboot.entity.Car;
import com.si.rkspringboot.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;


    @GetMapping
    public ResponseEntity<List<CarDto>> getCars() {
        List<CarDto> carsList = carService.selAll();
        if(carsList == null) {
            return new ResponseEntity<List<CarDto>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<CarDto>>(carsList, HttpStatus.OK);
        }
    }


    @GetMapping("/check/{value}")
    public ResponseEntity<CarDto> checkPlate(@PathVariable("value") String numPlate) {
        CarDto car = carService.checkNumPlate(numPlate);
        if(car == null) {
            return new ResponseEntity<CarDto>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<CarDto>(car, HttpStatus.OK);
        }
    }


    @GetMapping("/remove/{id}")
    public void removeCar(@PathVariable("id") Long id) {
        Car car = carService.getCar(id);
        carService.delCar(car);
    }


    @PostMapping("/add")
    public void addCar() {
        Car car = new Car();
        car.setBrand("Testin");
        car.setModel("Testin");
        car.setType("Testin");
        car.setNumPlate("Testin");
        carService.insCar(car);
    }

}
