package com.spd.uthservice.service;

import com.spd.uthservice.dto.LoginRequestDto;
import com.spd.uthservice.dto.LoginResponseDto;
import com.spd.uthservice.dto.UserRegistrationRequest;

public interface UserService {

    public String signUp(UserRegistrationRequest userRegistrationRequest);

    public LoginResponseDto login(LoginRequestDto loginRequestDto);

    public UserRegistrationRequest getUserRegistrationRequest(String email);
}
