package com.example.urlshortener.domain;

import java.util.Optional;

public interface UrlRepository {

    String save(String originalUrl);
    Optional<Url> getUrl(String shortenUrl);
    void addRequestCnt(String original);

}
