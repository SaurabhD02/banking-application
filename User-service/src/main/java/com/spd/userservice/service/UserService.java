package com.spd.userservice.service;

import com.spd.userservice.dto.UserDto;

import java.util.List;

public interface UserService {

    public UserDto createUser(UserDto userDto);
    public UserDto updateUser(UserDto userDto);
    public UserDto updateUser(String userName, UserDto userDto);
    public UserDto getUser(String userName);
    public List<UserDto> getAllUsers();
    public void deleteUser(String id);
}
