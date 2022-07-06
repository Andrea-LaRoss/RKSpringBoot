package com.si.rkspringboot.service;

import com.si.rkspringboot.dto.UserDto;

import java.util.List;

public interface UserService {

    public List<UserDto> selAll();

    public UserDto checkEmail(String email);

    public UserDto getUser(Long id);

    public void delUser(Long id);

    public void insUser(UserDto userDto);

}
