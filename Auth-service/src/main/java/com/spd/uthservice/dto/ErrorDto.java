package com.spd.uthservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {

    private String errorMessage;
    private String errorCode;
    private String timeStamp;
}
