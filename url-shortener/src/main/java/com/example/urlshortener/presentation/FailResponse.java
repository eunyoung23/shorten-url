package com.example.urlshortener.presentation;

import lombok.Getter;

@Getter
public class FailResponse {

    private final int statusCode;

    public FailResponse(int statusCode) {
        this.statusCode = statusCode;
    }

}
