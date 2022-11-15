package com.example.urlshortener.presentation;

import lombok.Getter;

@Getter
public class CreateUrlResponse {

    private final String shortenUrl;

    public CreateUrlResponse(String shortenUrl) {
        this.shortenUrl = shortenUrl;
    }
}
