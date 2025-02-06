//package com.spd.userservice.service;
//
//import com.spd.userservice.dto.UserDto;
//import com.spd.userservice.entity.User;
//import com.spd.userservice.repository.UserRepository;
//import com.spd.userservice.service.impl.UserServiceImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceTests {
//
//    @Mock
//    private UserRepository uRepo;
//
//    @InjectMocks
//    private UserServiceImpl userService;
//
//    public static  UserDto userDto;
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    @BeforeAll
//    public static void createUser(){
//        userDto = new UserDto();
//        userDto.setFirstName("John");
//        userDto.setLastName("Doe");
//        userDto.setEmail("john.doe@gmail.com");
//        userDto.setMobile("123456789");
//        userDto.setUsername("john");
//    }
//
//    @Test
//    @DisplayName("JUnit test for create user")
//    void createUserShouldCreateUserSuccessfully() {
//        //Precondition
//        User user = modelMapper.map(userDto, User.class);
//        Mockito.when(uRepo.save(user)).thenReturn(user);
//        //tetsting
//        UserDto savedUserDto = userService.createUser(userDto);
//        //assertions
//        Assertions.assertNotNull(savedUserDto);
//        Assertions.assertEquals(userDto.getFirstName(), savedUserDto.getFirstName());
//        Assertions.assertEquals(userDto.getLastName(), savedUserDto.getLastName());
//    }
//}
