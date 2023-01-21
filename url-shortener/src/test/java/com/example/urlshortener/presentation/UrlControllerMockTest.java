package com.example.urlshortener.presentation;

import com.example.urlshortener.application.UrlService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UrlController.class)
public class UrlControllerMockTest {

    @MockBean
    private UrlService urlService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("createUrl()메서드로 Originalurl을 요청값으로 보낼시 200코드가 반환되어야 한다.")
    public void createUrl() throws Exception {
        String content="{\"originalUrl\":\"https://google.com\"}";

        mockMvc.perform(post("/")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("단축 url로 요청시 원래의 url주소로 리다이렉트 되어야한다.")
    public void redirectTest()throws Exception{
        when(urlService.getOriginal(any())).thenReturn("https://google.com");

        mockMvc.perform(get("/{}","abcdef"))
                .andExpect(status().isMovedPermanently());
    }

    @Test
    @DisplayName("존재하지 않는 단축 url로 조회시 404에러가 반환되어야 한다.")
    public void notFoundShortenUrl() throws Exception{
        when(urlService.getOriginal(any())).thenReturn(null);

        mockMvc.perform(get("/{}","https://google.com"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("존재하지 않는 단축 url로 url조회수 조회시 404에러가 반환되어야 한다.")
    public void notFoundShortenUrlCount() throws Exception{
        when(urlService.getCnt(any())).thenThrow(new NotFoundException());

        mockMvc.perform(get("/count/{}","abcde"))
                .andExpect(status().isNotFound());
    }


}
