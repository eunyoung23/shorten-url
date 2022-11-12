package com.example.urlshortener.presentation;

import lombok.Getter;

@Getter
public class GetCntResponse {

    private final int statusCode;
    private final int count;

    public GetCntResponse(int statusCode, int count) {
        this.statusCode = statusCode;
        this.count = count;
    }
}
