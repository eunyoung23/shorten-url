package com.example.urlshortener.controller;

import com.example.urlshortener.config.BaseResponse;
import com.example.urlshortener.dto.UrlRequest;
import com.example.urlshortener.service.UrlService;
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

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createUrl(@RequestBody UrlRequest urlRequest){
        String shortenUrl=urlService.createUrl(urlRequest.getOriginalUrl());
        return new ResponseEntity<>(new BaseResponse(true,"success",200,shortenUrl),HttpStatus.OK);
    }

    @GetMapping("/redirect")
    public ResponseEntity<Object> redirectUrl(@RequestParam String shortenUrl) throws URISyntaxException {
        String original=urlService.getOriginal(shortenUrl);
        urlService.addRequestCnt(original);
        if(original==null){
            return new ResponseEntity<>(new BaseResponse(false,"not found",404,null),HttpStatus.NOT_FOUND);
        }else{
            HttpHeaders httpHeaders=new HttpHeaders();
            httpHeaders.setLocation(new URI(original));
            return new ResponseEntity<>(httpHeaders,HttpStatus.MOVED_PERMANENTLY);
        }
    }

    @GetMapping("/requestCnt")
    public ResponseEntity<BaseResponse> getRequestCnt(@RequestParam String shortenUrl){
        int cnt=urlService.getCnt(shortenUrl);
        if(cnt==-1){
            return new ResponseEntity<>(new BaseResponse(false,"not found",404,null), HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(new BaseResponse(true,"success",200,String.valueOf(cnt)),HttpStatus.OK);
        }
    }


}
