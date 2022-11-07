package com.example.urlshortener.application;

import com.example.urlshortener.domain.Url;
import com.example.urlshortener.infrastructure.UrlMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlService {

    private UrlMemoryRepository urlMemoryRepository;

    @Autowired
    public UrlService(UrlMemoryRepository urlMemoryRepository) {
        this.urlMemoryRepository = urlMemoryRepository;
    }

    public String createUrl(String originalUrl){
        return urlMemoryRepository.save(originalUrl);
    }

    public int getCnt(String shortenUrl){
        return urlMemoryRepository.getCnt(shortenUrl);
    }

    public String getOriginal(String shortenUrl){
        Optional<Url> url = urlMemoryRepository.getOriginalUrl(shortenUrl);
        return url.map(Url::getOriginalUrl).orElse(null);
    }

    public void addRequestCnt(String originalUrl){
        urlMemoryRepository.addRequestCnt(originalUrl);
    }


}
