package com.si.rkspringboot.service;

import com.si.rkspringboot.dto.CarDto;
import com.si.rkspringboot.entity.Car;
import com.si.rkspringboot.repository.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService{

    @Autowired
    private CarRepository carRepository;


    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<CarDto> selAll() {
        List<Car> carsList = carRepository.findAll();
        return this.convertToDtoList(carsList);
    }


    @Override
    public CarDto checkNumPlate(String numPlate) {
        Car car = carRepository.searchCarByNumPlate(numPlate);
        return this.convertToDto(car);
    }


    @Override
    public CarDto getCar(Long id) {
        Car car = carRepository.getReferenceById(id);
        return this.convertToDto(car);
    }


    @Override
    public void delCar(Long id) { carRepository.deleteById(id); }


    @Override
    public void insCar(CarDto carDto) {
        Car car = convertToCar(carDto);
        carRepository.save(car); }


    private CarDto convertToDto(Car car) {
        CarDto carDto = null;
        if (car != null) {
            carDto =  modelMapper.map(car, CarDto.class);
        }
        return carDto;
    }


    private Car convertToCar(CarDto carDto) {
        Car car = null;
        if (carDto != null) {
            car =  modelMapper.map(carDto, Car.class);
        }
        return car;
    }


    private List<CarDto> convertToDtoList(List<Car> carsList) {
        List<CarDto> carsDto = new ArrayList<>();
        if (carsList != null) {
            carsDto = carsList
                    .stream()
                    .map(source -> modelMapper.map(source, CarDto.class))
                    .collect(Collectors.toList());
        }
        return carsDto;
    }

}
