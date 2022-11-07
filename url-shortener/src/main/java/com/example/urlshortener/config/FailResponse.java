package com.example.urlshortener.config;

import lombok.Getter;

@Getter
public class FailResponse {

    private final Boolean isSuccess;
    private final String message;
    private final int code;

    public FailResponse(Boolean isSuccess, String message, int code) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.code = code;
    }

}
