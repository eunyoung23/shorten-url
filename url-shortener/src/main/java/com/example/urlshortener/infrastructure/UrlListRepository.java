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
        return urlStore.stream()
                .filter(a->a.getShortenUrl().equals(shortenUrl))
                .findFirst();
    }

    @Override
    public Optional<Url> getUrlByOriginalUrl(String originalUrl) {
        return urlStore.stream()
                .filter(a -> a.getOriginalUrl().equals(originalUrl))
                .findFirst();
    }

    @Override
    public synchronized void addRequestCnt(String original) {
        Url url = urlStore.stream()
                .filter(e->e.getOriginalUrl().equals(original))
                .findFirst()
                .get();
        int num = url.getRequestCnt();
        String shortenUrl = url.getShortenUrl();
        int index = urlStore.indexOf(url);
        Url getUrl = Url.urlBuiler(original,shortenUrl,++num);
        urlStore.set(index,getUrl);
    }

    @Override
    public boolean isExistOriginalUrl(String originalUrl) {
        if(urlStore.stream().map(url->url.getOriginalUrl().equals(originalUrl)).count()>0){
            return true;
        }
        return false;
    }

}
