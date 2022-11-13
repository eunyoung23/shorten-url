package com.example.urlshortener.domain;

import java.util.Optional;

public interface UrlRepository {

    void save(Url url);
    Optional<Url> getUrl(String shortenUrl);
    String getShortenByOriginalUrl(String originalUrl);
    void addRequestCnt(String original);
    boolean isExistOriginalUrl(String originalUrl);

}
