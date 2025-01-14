package com.spd.uthservice.client;

import com.spd.uthservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://localhost:8084/", name = "USER-SERVICE")
public interface ApiClient {

    @PostMapping("/api/users")
    public UserDto createUser(@RequestBody UserDto userDto);
}
