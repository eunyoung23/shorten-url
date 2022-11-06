package com.example.urlshortener.repository;

import com.example.urlshortener.entity.Url;

import java.util.Optional;

public interface UrlRepository {

    String save(String originalUrl);
    int getCnt(String shortenUrl);
    Optional<Url> getOriginalUrl(String shortenUrl);
    void addRequestCnt(String original);

}
