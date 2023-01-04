package com.example.urlshortener.presentation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UrlControllerTest {

    @Autowired
    private UrlController urlController;

    @Test
    @DisplayName("존재하지 않는 단축 url로 조회시에 404 에러가 난다.")
    void notExistUrlTest() {
        String shortenUrl = "ABCD";

        Assertions.assertThrows(RuntimeException.class, () -> {
            urlController.redirectUrl(shortenUrl);
        });
    }

}