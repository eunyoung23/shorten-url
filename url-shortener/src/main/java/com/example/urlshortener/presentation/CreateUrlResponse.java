package com.example.urlshortener.presentation;

import lombok.Getter;

@Getter
public class CreateUrlResponse {

    private final Boolean isSuccess;
    private final String message;
    private final int code;
    private final String shortenUrl;

    public CreateUrlResponse(Boolean isSuccess, String message, int code, String data) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.code = code;
        this.shortenUrl = data;
    }

}
