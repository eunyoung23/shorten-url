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
        Url url = urlMap.get(shortenUrl);
        return Optional.ofNullable(url);
    }

    @Override
    public Optional<Url> getUrlByOriginalUrl(String originalUrl) {
        Url url = urlMap.values().stream()
                .filter(e-> e.getOriginalUrl().equals(originalUrl))
                .findAny().get();
        return Optional.ofNullable(url);
    }

    @Override
    public synchronized void addRequestCnt(String original) {
        Url url = urlMap.values().stream()
                .filter(e->e.getOriginalUrl().equals(original))
                .findAny().get();
        int num = url.getRequestCnt();
        String shortenUrl = url.getShortenUrl();
        Url getUrl = Url.urlBuiler(original,shortenUrl,++num);
        urlMap.put(shortenUrl,getUrl);
    }

    @Override
    public boolean isExistOriginalUrl(String originalUrl) {
        if(urlMap.values().stream().filter(e->e.getOriginalUrl().equals(originalUrl)).count()>0){
            return true;
        }
        return false;
    }


}
