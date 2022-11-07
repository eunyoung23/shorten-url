package com.example.urlshortener.presentation;

import lombok.Getter;

@Getter
public class GetCntResponse {

    private final Boolean isSuccess;
    private final String message;
    private final int code;
    private final int count;

    public GetCntResponse(Boolean isSuccess, String message, int code, int count) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.code = code;
        this.count = count;
    }

}
