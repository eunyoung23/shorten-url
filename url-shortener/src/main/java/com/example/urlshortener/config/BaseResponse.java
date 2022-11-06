package com.example.urlshortener.config;

import lombok.Getter;

@Getter
public class BaseResponse {

    private final Boolean isSuccess;
    private final String message;
    private final int code;
    private final String data;

    public BaseResponse(Boolean isSuccess, String message, int code, String data) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.code = code;
        this.data = data;
    }

}
