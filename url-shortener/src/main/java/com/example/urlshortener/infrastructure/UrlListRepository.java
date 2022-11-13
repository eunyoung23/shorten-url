package com.example.urlshortener.infrastructure;

import com.example.urlshortener.domain.Url;
import com.example.urlshortener.domain.UrlRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Repository
public class UrlListRepository implements UrlRepository {

    private static List<Url> urlStore=new ArrayList<>();

    @Override
    public synchronized void save(Url url) {
        urlStore.add(url);
    }

    @Override
    public Optional<Url> getUrl(String shortenUrl) {
        return urlStore.stream().filter(a->a.getShortenUrl().equals(shortenUrl)).findFirst();
    }

    @Override
    public Optional<Url> getUrlByOriginalUrl(String originalUrl) {
        return urlStore.stream().filter(a -> a.getOriginalUrl().equals(originalUrl)).findFirst();
    }

    @Override
    public synchronized void addRequestCnt(String original) {
        for(Url url:urlStore){
            if(url.getOriginalUrl().equals(original)){
                int num=url.getRequestCnt();
                urlStore.set(urlStore.indexOf(url),Url.urlBuiler(original,url.getShortenUrl(),++num));
            }
        }
    }

    @Override
    public boolean isExistOriginalUrl(String originalUrl) {
        if(urlStore.stream().map(url->url.getOriginalUrl().equals(originalUrl)).count()>0){
            return true;
        }
        return false;
    }


}
