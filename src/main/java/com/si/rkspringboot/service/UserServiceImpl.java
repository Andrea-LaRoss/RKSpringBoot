package com.si.rkspringboot.service;

import com.si.rkspringboot.dto.UserDto;
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
    public List<UserDto> searchByEmail(String email) {
        List<User> usersList = userRepository.searchByEmailContaining(email);
        return this.convertToDtoList(usersList);
    }


    @Override
    public List<UserDto> searchByFirstName(String firstName) {
        List<User> usersList = userRepository.searchByFirstNameContaining(firstName);
        return this.convertToDtoList(usersList);
    }


    @Override
    public List<UserDto> searchByLastName(String lastName) {
        List<User> usersList = userRepository.searchByLastNameContaining(lastName);
        return this.convertToDtoList(usersList);
    }


    @Override
    public List<UserDto> searchByBirthday(LocalDate birthday) {
        List<User> usersList = userRepository.searchAllByBirthday(birthday);
        return this.convertToDtoList(usersList);
    }


    @Override
    public UserDto checkEmail(String email) {
        User user = userRepository.searchUserByEmail(email);
        return this.convertToDto(user);
    }


    @Override
    public User getUser(Long id) {
        return userRepository.getReferenceById(id);
    }


    @Override
    public void delUser(User user) { userRepository.delete(user); }


    @Override
    public void insUser(User user) { userRepository.save(user); }


    private UserDto convertToDto(User user) {
        UserDto userDto = null;
        if (user != null) {
            userDto =  modelMapper.map(user, UserDto.class);
        }
        return userDto;
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
