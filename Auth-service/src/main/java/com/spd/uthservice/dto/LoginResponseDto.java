package com.spd.uthservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class LoginResponseDto {

    private String username;
    private Set<String> roles;
    private String message;
    private String statusCode;
    private String token;
    private ErrorDto errorDto;
}
