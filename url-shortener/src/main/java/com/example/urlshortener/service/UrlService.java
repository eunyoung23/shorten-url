package com.example.urlshortener.service;

import com.example.urlshortener.entity.Url;
import com.example.urlshortener.repository.UrlMemoryRepository;
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
        String originalUrl=null;
        Optional<Url> url = urlMemoryRepository.getOriginalUrl(shortenUrl);
        if(url.isPresent()){
            originalUrl=url.get().getOriginalUrl();
        }
        return originalUrl;
    }

    public void addRequestCnt(String originalUrl){
        urlMemoryRepository.addRequestCnt(originalUrl);
    }


}
