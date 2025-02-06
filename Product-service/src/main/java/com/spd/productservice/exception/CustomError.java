package com.spd.productservice.exception;

import java.time.LocalDateTime;
import java.util.List;

import lombok.*;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomError {

    @Builder.Default
    private LocalDateTime time = LocalDateTime.now();

    private HttpStatus httpStatus;

    private String header;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @Builder.Default
    private final Boolean isSuccess = false;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CustomSubError> subErrors;

    /**
     * Represents a sub-error with specific details as {@link CustomSubError}.
     */
    @Getter
    @Builder
    public static class CustomSubError {

        private String message;

        private String field;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Object value;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String type;

    }

    /**
     * Enumerates common error headers for categorizing errors as {@link Header}.
     */
    public enum Header {

        API_ERROR("API ERROR"),

        ALREADY_EXIST("ALREADY EXIST"),

        NOT_FOUND("NOT EXIST"),

        VALIDATION_ERROR("VALIDATION ERROR"),

        DATABASE_ERROR("DATABASE ERROR"),

        PROCESS_ERROR("PROCESS ERROR"),

        AUTH_ERROR("AUTH ERROR"),

        PRODUCT_NOT_FOUND("PRODUCT NOT FOUND");

        private final String name;

        Header(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}

