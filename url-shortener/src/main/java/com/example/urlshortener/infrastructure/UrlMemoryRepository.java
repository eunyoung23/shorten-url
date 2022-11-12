package com.example.urlshortener.infrastructure;

import com.example.urlshortener.domain.Url;
import com.example.urlshortener.domain.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class UrlMemoryRepository implements UrlRepository {

    private static List<Url> urlStore=new ArrayList<>();
    private static long sequence=1L;
    private Base62 base62;

    @Autowired
    public UrlMemoryRepository(Base62 base62) {
        this.base62 = base62;
    }

    @Override
    public synchronized String save(String originalUrl) {
        Stream<Url> getOriginalUrlStream = urlStore.stream().filter(url -> originalUrl.equals(url.getOriginalUrl()));
        if(getOriginalUrlStream.count()>0){
             return getOriginalUrlStream.findFirst().get().getShortenUrl();
        }else{
            String shortenUrl=base62.encoding(sequence++);
            Url url=Url.urlBuiler(originalUrl,shortenUrl,0);
            urlStore.add(url);
            return shortenUrl;
        }
    }

    @Override
    public Optional<Url> getUrl(String shortenUrl) {
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
