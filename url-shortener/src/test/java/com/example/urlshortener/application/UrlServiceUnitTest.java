package com.example.urlshortener.application;

import com.example.urlshortener.infrastructure.Base62;
import com.example.urlshortener.infrastructure.UrlListRepository;
import com.example.urlshortener.presentation.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UrlServiceUnitTest {

    @Mock
    private UrlListRepository urlListRepository;

    @Mock
    private Base62 base62;

    @InjectMocks
    private UrlService urlService;

    @Test
    @DisplayName("originalUrl이 존재하지 않을때 NotFound에러가 발생해야한다.")
    public void notFoundOriginalUrl(){
        when(urlListRepository.getUrl(any())).thenThrow(NotFoundException.class);

        Assertions.assertThrows(NotFoundException.class,()->urlService.getOriginal("abcde"));
    }

}
