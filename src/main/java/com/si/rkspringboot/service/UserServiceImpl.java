package com.si.rkspringboot.service;

import com.si.rkspringboot.dto.CarDto;
import com.si.rkspringboot.dto.UserDto;
import com.si.rkspringboot.entity.Car;
import com.si.rkspringboot.entity.User;
import com.si.rkspringboot.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<UserDto> selAll() {
        List<User> usersList = userRepository.findAll();
        return this.convertToDtoList(usersList);
    }


    @Override
    public UserDto checkEmail(String email) {
        User user = userRepository.searchUserByEmail(email);
        return this.convertToDto(user);
    }


    @Override
    public UserDto getUser(Long id) {
        User user = userRepository.getReferenceById(id);
        return this.convertToDto(user);
    }


    @Override
    public void delUser(Long id) { userRepository.deleteById(id); }


    @Override
    public void insUser(UserDto userDto) {
        User user = convertToUser(userDto);
        userRepository.save(user); }


    private UserDto convertToDto(User user) {
        UserDto userDto = null;
        if (user != null) {
            userDto =  modelMapper.map(user, UserDto.class);
        }
        return userDto;
    }


    private User convertToUser(UserDto userDto) {
        User user = null;
        if (userDto != null) {
            user =  modelMapper.map(userDto, User.class);
        }
        return user;
    }


    private List<UserDto> convertToDtoList(List<User> usersList) {
        List<UserDto> usersDto = new ArrayList<>();
        if (usersList != null) {
            usersDto = usersList
                    .stream()
                    .map(source -> modelMapper.map(source, UserDto.class))
                    .collect(Collectors.toList());
        }
        return usersDto;
    }

}
