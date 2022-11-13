package com.example.urlshortener.infrastructure;

import com.example.urlshortener.domain.Url;
import com.example.urlshortener.domain.UrlRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UrlMapRepository implements UrlRepository {

    private static Map<String,Url> urlMap=new ConcurrentHashMap<>();

    @Override
    public synchronized void save(Url url) {
        urlMap.put(url.getShortenUrl(),url);
    }

    @Override
    public Optional<Url> getUrl(String shortenUrl) {
       return Optional.ofNullable(urlMap.get(shortenUrl));
    }

    @Override
    public Optional<Url> getUrlByOriginalUrl(String originalUrl) {
        for(Url url:urlMap.values()){
            if(url.getOriginalUrl().equals(originalUrl)){
                return Optional.of(url);
            }
        }
        return null;
    }

    @Override
    public synchronized void addRequestCnt(String original) {
        for(Url url:urlMap.values()){
            if(url.getOriginalUrl().equals(original)){
                int num= url.getRequestCnt();
                urlMap.put(url.getShortenUrl(), Url.urlBuiler(original,url.getShortenUrl(),++num));
            }
        }
    }

    @Override
    public boolean isExistOriginalUrl(String originalUrl) {
        for(Url url:urlMap.values()){
            if(url.getOriginalUrl().equals(originalUrl)){
                return true;
            }
        }
        return false;
    }


}
