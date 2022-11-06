package com.example.urlshortener.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Url {

    private String originalUrl;

    private String shortenUrl;

    private int requestCnt;

    public static Url urlBuiler(String originalUrl,String shortenUrl,int cnt){
        return new UrlBuilder()
                .originalUrl(originalUrl)
                .shortenUrl(shortenUrl)
                .requestCnt(cnt)
                .build();
    }

}
