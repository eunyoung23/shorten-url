package com.example.urlshortener.domain;

import com.example.urlshortener.domain.Url;

import java.util.Optional;

public interface UrlRepository {

    String save(String originalUrl);
    int getCnt(String shortenUrl);
    Optional<Url> getOriginalUrl(String shortenUrl);
    void addRequestCnt(String original);

}
