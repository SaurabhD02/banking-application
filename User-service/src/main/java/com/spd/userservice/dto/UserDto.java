package com.spd.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String gender;
    private List<AddressDto> addressesDto;
}
