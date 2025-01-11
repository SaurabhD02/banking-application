package com.spd.uthservice.service;

import com.spd.uthservice.dto.ErrorDto;
import com.spd.uthservice.dto.LoginRequestDto;
import com.spd.uthservice.dto.LoginResponseDto;
import com.spd.uthservice.dto.UserRegistrationRequest;
import com.spd.uthservice.entity.UserEntity;
import com.spd.uthservice.repository.UserRepository;
import com.spd.uthservice.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.apache.http.HttpStatus;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;
    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;


    @Override
    public String signUp(UserRegistrationRequest userRegistrationRequest) {
        UserEntity user = modelMapper.map(userRegistrationRequest, UserEntity.class);
        user.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = null;
        Authentication authentication = null;
        try{
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            if(authentication.isAuthenticated()){
                loginResponseDto = LoginResponseDto.builder()
                        .username(((UserDetails) authentication.getPrincipal()).getUsername())
                        .roles(authentication.getAuthorities().stream()
                                .map(e-> e.getAuthority()).collect(Collectors.toSet()))
                        .token(jwtUtil.generateToken(loginRequestDto.getUsername()))
                        .statusCode(String.valueOf(HttpStatus.SC_OK))
                        .message("Login Successfully !")
                        .build();
            }
        } catch(AuthenticationException e){
            ErrorDto errorDto = ErrorDto.builder().errorMessage("Invalid credentials !")
                    .timeStamp(String.valueOf(new Timestamp(new Date().getTime())))
                    .errorCode(String.valueOf(HttpStatus.SC_NOT_FOUND)).build();
            loginResponseDto = LoginResponseDto.builder().errorDto(errorDto).build();
        }
        return loginResponseDto;
    }

    @Override
    public UserRegistrationRequest getUserRegistrationRequest(String email) {
        UserRegistrationRequest userRegistrationRequest = null;

        Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()){
            userRegistrationRequest = modelMapper.map(optionalUser.get(), UserRegistrationRequest.class);
        } else{
            throw new UsernameNotFoundException("User not found with valid email address -> "+ email);
        }

        return userRegistrationRequest;
    }

//    private List<UserEntity> getAllUsers() {
//
//        Pageable pageable =  PageRequest.of(0, 10);
//        List<UserEntity> userEntityList = userRepository.findAll(Pageable);
//    }
}
