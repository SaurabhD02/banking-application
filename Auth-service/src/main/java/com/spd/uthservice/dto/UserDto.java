package com.spd.uthservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String gender;
    private Set<AddressDto> addressesDto;
}
