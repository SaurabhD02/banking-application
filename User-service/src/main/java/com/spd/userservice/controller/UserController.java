package com.spd.userservice.controller;

import com.spd.userservice.dto.UserDto;
import com.spd.userservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        log.info("*** UserDto , controller; save user *");
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("username") String username) {
        log.info("*** UserDto , controller; fetch user by username *");
        return new ResponseEntity<>(userService.getUser(username), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        log.info("*** UserDto , controller; update user  *");
        return new ResponseEntity<>(userService.updateUser(userDto), HttpStatus.OK);
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("username") String userName,@RequestBody UserDto userDto) {
        log.info("*** UserDto , controller; update user by username  *");
        return new ResponseEntity<>(userService.updateUser(userName,userDto), HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable("username") String userName) {
        log.info("*** UserDto , controller; delete user by username  *");
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        log.info("*** UserDtoList , controller; fetch all users  *");
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }


}
