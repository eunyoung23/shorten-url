package com.example.urlshortener.presentation;

import lombok.Getter;

@Getter
public class GetCntResponse {

    private final int count;

    public GetCntResponse(int count) {
        this.count = count;
    }
}
