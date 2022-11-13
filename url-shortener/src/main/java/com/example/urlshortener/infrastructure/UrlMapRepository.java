package com.example.urlshortener.infrastructure;

import com.example.urlshortener.domain.Url;
import com.example.urlshortener.domain.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UrlMapRepository implements UrlRepository {

    private static Map<String,Url> urlMap=new ConcurrentHashMap<>();
    private static long sequence=1L;
    private Base62 base62;

    @Autowired
    public UrlMapRepository(Base62 base62) {
        this.base62 = base62;
    }

    @Override
    public synchronized String save(String originalUrl) {
        for(Url url:urlMap.values()){
            if(url.getOriginalUrl().equals(originalUrl)){
                return url.getShortenUrl();
            }
        }
        String shortenUrl=base62.encoding(sequence++);
        Url url=Url.urlBuiler(originalUrl,shortenUrl,0);
        urlMap.put(shortenUrl,url);
        return shortenUrl;
    }

    @Override
    public Optional<Url> getUrl(String shortenUrl) {
        return Optional.ofNullable(urlMap.get(shortenUrl));
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

}
