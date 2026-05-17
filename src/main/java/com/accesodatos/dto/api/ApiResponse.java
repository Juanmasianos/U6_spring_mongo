package com.accesodatos.dto.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse <T>{

    private LocalDateTime timestamp;
    private String message;
    private int code;
    private T data;

    public ApiResponse(String message, int code, T data) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.code = code;
        this.data = data;
    }


}
