package com.spd.uthservice.controller;

import com.spd.uthservice.dto.LoginRequestDto;
import com.spd.uthservice.dto.LoginResponseDto;
import com.spd.uthservice.dto.UserRegistrationRequest;
import com.spd.uthservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {

    private UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody UserRegistrationRequest userRegistrationRequest){
        return new ResponseEntity<>(userService.signUp(userRegistrationRequest), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return new ResponseEntity<>(userService.login(loginRequestDto), HttpStatus.OK);
    }

    @GetMapping("/home")
    public ResponseEntity<String> home(){
        return new ResponseEntity<>("User Authentication Done !",HttpStatus.OK);
    }

    @GetMapping("/users/{email}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserRegistrationRequest> getUser(@PathVariable("email") String email){
        return new ResponseEntity<>(userService.getUserRegistrationRequest(email),HttpStatus.OK);
    }


}
