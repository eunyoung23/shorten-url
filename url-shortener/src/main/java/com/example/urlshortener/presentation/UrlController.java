package com.example.urlshortener.presentation;

import com.example.urlshortener.domain.dto.UrlRequest;
import com.example.urlshortener.application.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class UrlController {

    private UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/")
    public ResponseEntity<CreateUrlResponse> createUrl(@RequestBody UrlRequest urlRequest){
        String shortenUrl=urlService.createUrl(urlRequest.getOriginalUrl());
        return new ResponseEntity<>(new CreateUrlResponse(200,shortenUrl),HttpStatus.OK);
    }

    @GetMapping("/{shortenUrl}")
    public ResponseEntity<Object> redirectUrl(@PathVariable String shortenUrl) throws URISyntaxException {
        String original=urlService.getOriginal(shortenUrl);
        urlService.addRequestCnt(original);

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setLocation(new URI(original));
        return new ResponseEntity<>(httpHeaders,HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/count/{shortenUrl}")
    public ResponseEntity<Object> getRequestCnt(@PathVariable String shortenUrl){
        int cnt=urlService.getCnt(shortenUrl);
        return new ResponseEntity<>(new GetCntResponse(200,cnt), HttpStatus.OK);
    }


}
