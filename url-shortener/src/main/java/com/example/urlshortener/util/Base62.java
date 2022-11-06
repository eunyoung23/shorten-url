package com.example.urlshortener.util;

import org.springframework.stereotype.Component;

@Component
public class Base62 {

    private final String URL_PREFIX="bit.ly/";
    private final int BASE62=62;
    private final String BASE62_CHAR="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public String encoding(long param){
        StringBuffer sb=new StringBuffer();
        while(param>0){
            sb.append(BASE62_CHAR.charAt((int) (param%BASE62)));
            param/=BASE62;
        }
        return URL_PREFIX+sb.toString();
    }


}
