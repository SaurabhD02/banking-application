package com.spd.userservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String addressType;
    @JsonProperty("user")
    private UserDto userDto;
}
