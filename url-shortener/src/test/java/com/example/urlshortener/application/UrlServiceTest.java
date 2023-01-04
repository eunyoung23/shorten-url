package com.example.urlshortener.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UrlServiceTest {

    @Autowired
    private UrlService urlService;

    @Test
    @DisplayName("단축 url로 원래 url을 조회를 하면 원래의 url이 조회되어야 한다.")
    void getOriginalUrlTest(){
        String originalUrl="https://www.naver.com/";

        String shortenUrl=urlService.createUrl(originalUrl);
        String getUrlByShortenUrl=urlService.getOriginal(shortenUrl);

        assertTrue(getUrlByShortenUrl.equals(originalUrl));
    }


    @Test
    @DisplayName("단축 url로 원래 url을 조회시 원래 url이 없으면 예외가 발생해야한다.")
    void getOriginalUrlByShortenTest(){
        String shortenUrl="ABCD";

        Assertions.assertThrows(RuntimeException.class,()->urlService.getOriginal(shortenUrl));
    }

}