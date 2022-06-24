package com.si.rkspringboot.controller;

import com.si.rkspringboot.dto.CarDto;
import com.si.rkspringboot.entity.Car;
import com.si.rkspringboot.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
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


    @GetMapping("/brand/{value}")
    public ResponseEntity<List<CarDto>> searchBrand(@PathVariable("value") String brand) {
        List<CarDto> carsList = carService.searchByBrand(brand);
        if(carsList == null) {
            return new ResponseEntity<List<CarDto>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<CarDto>>(carsList, HttpStatus.OK);
        }
    }


    @GetMapping("/model/{value}")
    public ResponseEntity<List<CarDto>> searchModel(@PathVariable("value") String model) {
        List<CarDto> carsList = carService.searchByModel(model);
        if(carsList == null) {
            return new ResponseEntity<List<CarDto>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<CarDto>>(carsList, HttpStatus.OK);
        }
    }


    @GetMapping("/type/{value}")
    public ResponseEntity<List<CarDto>> searchType(@PathVariable("value") String type) {
        List<CarDto> carsList = carService.searchByType(type);
        if(carsList == null) {
            return new ResponseEntity<List<CarDto>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<CarDto>>(carsList, HttpStatus.OK);
        }
    }


    @GetMapping("/numplate/{value}")
    public ResponseEntity<List<CarDto>> searchNumPlate(@PathVariable("value") String numPlate) {
        List<CarDto> carsList = carService.searchByNumPlate(numPlate);
        if(carsList == null) {
            return new ResponseEntity<List<CarDto>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<CarDto>>(carsList, HttpStatus.OK);
        }
    }


    @GetMapping("/regdate/{value}")
    public ResponseEntity<List<CarDto>> searchRegDate(@PathVariable("value") String regDate) {
        List<CarDto> carsList = carService.searchByRegDate(regDate);
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


    @GetMapping("/add")
    public void addCar() {
        Car car = new Car();
        car.setBrand("Test");
        car.setModel("Test");
        car.setType("Test");
        car.setNumPlate("Test");
        carService.insCar(car);
    }

}
