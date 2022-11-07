package com.example.urlshortener.domain;

import java.util.Optional;

public interface UrlRepository {

    String save(String originalUrl);
    int getCnt(String shortenUrl);
    Optional<Url> getOriginalUrl(String shortenUrl);
    void addRequestCnt(String original);

}
