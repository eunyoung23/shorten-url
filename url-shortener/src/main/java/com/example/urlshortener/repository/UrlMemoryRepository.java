package com.example.urlshortener.repository;

import com.example.urlshortener.entity.Url;
import com.example.urlshortener.util.Base62;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UrlMemoryRepository implements UrlRepository{

    private static List<Url> urlStore=new ArrayList<>();
    private static long sequence=1L;
    private Base62 base62;

    @Autowired
    public UrlMemoryRepository(Base62 base62) {
        this.base62 = base62;
    }

    @Override
    public synchronized String save(String originalUrl) {
        if(urlStore.stream().filter(url->originalUrl.equals(url.getOriginalUrl())).count()>0){
             return urlStore.stream().filter(url -> originalUrl.equals(url.getOriginalUrl())).findFirst().get().getShortenUrl();
        }else{
            String shortenUrl=base62.encoding(sequence++);
            Url url=Url.urlBuiler(originalUrl,shortenUrl,0);
            urlStore.add(url);
            return shortenUrl;
        }
    }

    @Override
    public int getCnt(String shorten) {
        Optional<Url> findUrl= urlStore.stream().filter(url -> url.getShortenUrl().equals(shorten)).findAny();
        if(findUrl.isPresent()){
            return findUrl.get().getRequestCnt();
        } else{
            return -1;
        }
    }

    @Override
    public Optional<Url> getOriginalUrl(String shortenUrl) {
        return urlStore.stream().filter(a->a.getShortenUrl().equals(shortenUrl)).findFirst();
    }

    @Override
    public synchronized void addRequestCnt(String original) {
        for(Url url:urlStore){
            if(url.getOriginalUrl()==original){
                int num=url.getRequestCnt();
                urlStore.set(urlStore.indexOf(url),Url.urlBuiler(original,url.getShortenUrl(),++num));
            }
        }
    }


}
