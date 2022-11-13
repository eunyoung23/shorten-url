package com.example.urlshortener.application;

import com.example.urlshortener.domain.Url;
import com.example.urlshortener.domain.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private UrlRepository urlRepository;

    @Autowired
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String createUrl(String originalUrl){
        return urlRepository.save(originalUrl);
    }

    public int getCnt(String shortenUrl){
        return urlRepository.getUrl(shortenUrl).map(Url::getRequestCnt).get();
    }

    public String getOriginal(String shortenUrl){
        return urlRepository.getUrl(shortenUrl).map(Url::getOriginalUrl).orElse(null);
    }

    public void addRequestCnt(String originalUrl){
        urlRepository.addRequestCnt(originalUrl);
    }


}
