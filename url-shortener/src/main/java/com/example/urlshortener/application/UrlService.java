package com.example.urlshortener.application;

import com.example.urlshortener.domain.Url;
import com.example.urlshortener.domain.UrlRepository;
import com.example.urlshortener.infrastructure.Base62;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UrlService {

    private UrlRepository urlRepository;
    private Base62 base62;

    @Autowired
    public UrlService(UrlRepository urlRepository, Base62 base62) {
        this.urlRepository = urlRepository;
        this.base62 = base62;
    }

    public String createUrl(String originalUrl){
        if(urlRepository.isExistOriginalUrl(originalUrl)){
            return urlRepository.getUrlByOriginalUrl(originalUrl).get().getShortenUrl();
        }
        String shortenUrl=base62.encoding(new Random(System.currentTimeMillis()).nextInt(100000000));
        urlRepository.save(Url.urlBuiler(originalUrl,shortenUrl,0));
        return shortenUrl;
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
