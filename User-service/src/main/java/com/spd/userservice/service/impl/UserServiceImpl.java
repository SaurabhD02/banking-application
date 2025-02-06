package com.spd.userservice.service.impl;

import com.spd.userservice.dto.UserDto;
import com.spd.userservice.entity.Address;
import com.spd.userservice.entity.User;
import com.spd.userservice.exception.UserNotFoundException;
import com.spd.userservice.repository.UserRepository;
import com.spd.userservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        log.info("*** UserDto , serviceImpl; save user *");
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        log.info("*** UserDto , serviceImpl; update user  *");
        User user = modelMapper.map(userDto, User.class);
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(String userName, UserDto userDto) {
        log.info("*** UserDto , serviceImpl; update user by username  *");
        User user = userRepository.findByUsername(userName).orElse(null);
        if (user != null) {
            user.setAddresses(userDto.getAddressesDto().stream()
                    .map(addressDto -> modelMapper.map(addressDto, Address.class)).collect(Collectors.toList()));
            for(Address address : user.getAddresses()) {
                address.setUser(user);
            }
            user.setEmail(userDto.getEmail());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setGender(userDto.getGender());
            user.setMobile(userDto.getMobile());
            User updatedUser = userRepository.save(user);
            return modelMapper.map(updatedUser, UserDto.class);
        } else {
            log.error("User not found with userName : {}", userName);
            throw new UserNotFoundException("User not found with userName : "+ userName);
        }

    }

    @Override
    public UserDto getUser(String userName) {
        log.info("*** UserDto , serviceImpl; fetch user by username *");
        Optional<User> optionalUser = userRepository.findByUsername(userName);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return modelMapper.map(user, UserDto.class);
        }else {
            log.error("User not found with userName : {}", userName);
            throw new UserNotFoundException("User not found with userName : "+ userName);
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("*** UserDto , serviceImpl; delete user by username  *");
       List<User> users = userRepository.findAll();
       return users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(String id) {
        log.info("*** UserDtoList , serviceImpl; fetch all users  *");
        User user = userRepository.findByUsername(id).orElse(null);
        if (user != null) {
//            userRepository.de(id);
        }else {
            log.error("User not found with userName : {}", id);
            throw new UserNotFoundException("User not found with userName : "+ id);
        }
    }
}
