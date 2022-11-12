package com.example.urlshortener.presentation;

import lombok.Getter;

@Getter
public class CreateUrlResponse {

    private final int statusCode;
    private final String shortenUrl;

    public CreateUrlResponse(int statusCode, String shortenUrl) {
        this.statusCode = statusCode;
        this.shortenUrl = shortenUrl;
    }
}
