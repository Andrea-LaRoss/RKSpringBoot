package com.si.rkspringboot.service;

import com.si.rkspringboot.dto.UserDto;
import com.si.rkspringboot.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    public List<UserDto> selAll();

    public List<UserDto> searchByEmail(String email);

    public List<UserDto> searchByFirstName(String firstName);

    public List<UserDto> searchByLastName(String lastName);

    public List<UserDto> searchByBirthday(LocalDate birthday);

    public UserDto checkEmail(String email);

    public User getUser(Long id);

    public void delUser(User user);

    public void insUser(User user);

}
