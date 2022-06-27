package com.si.rkspringboot.service;

import com.si.rkspringboot.dto.CarDto;
import com.si.rkspringboot.entity.Car;
import com.si.rkspringboot.repository.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public List<CarDto> searchByBrand(String brand) {
        List<Car> carsList = carRepository.searchByBrandContaining(brand);
        return this.convertToDtoList(carsList);
    }


    @Override
    public List<CarDto> searchByModel(String model) {
        List<Car> carsList = carRepository.searchByModelContaining(model);
        return this.convertToDtoList(carsList);
    }


    @Override
    public List<CarDto> searchByType(String type) {
        List<Car> carsList = carRepository.searchByTypeContaining(type);
        return this.convertToDtoList(carsList);
    }


    @Override
    public List<CarDto> searchByNumPlate(String numPlate) {
        List<Car> carsList = carRepository.searchByNumPlateContaining(numPlate);
        return this.convertToDtoList(carsList);
    }


    @Override
    public List<CarDto> searchByRegDate(String regDate) {
        List<Car> carsList = carRepository.searchAllByRegDate(regDate);
        return this.convertToDtoList(carsList);
    }


    @Override
    public CarDto checkNumPlate(String numPlate) {
        Car car = carRepository.searchCarByNumPlate(numPlate);
        return this.convertToDto(car);
    }


    @Override
    public Car getCar(Long id) {
        return carRepository.getReferenceById(id);
    }


    @Override
    public void delCar(Car car) { carRepository.delete(car); }


    @Override
    public void insCar(Car car) { carRepository.save(car); }


    private CarDto convertToDto(Car car) {
        CarDto carDto = null;
        if (car != null) {
            carDto =  modelMapper.map(car, CarDto.class);
        }
        return carDto;
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
