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

    @PostMapping("/`")
    public ResponseEntity<CreateUrlResponse> createUrl(@RequestBody UrlRequest urlRequest){
        String shortenUrl=urlService.createUrl(urlRequest.getOriginalUrl());
        return new ResponseEntity<>(new CreateUrlResponse(true,"success",200,shortenUrl),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> redirectUrl(@RequestParam String shortenUrl) throws URISyntaxException {
        String original=urlService.getOriginal(shortenUrl);
        urlService.addRequestCnt(original);
        if(original==null){
            return new ResponseEntity<>(new FailResponse(false,"not found",404),HttpStatus.NOT_FOUND);
        }else{
            HttpHeaders httpHeaders=new HttpHeaders();
            httpHeaders.setLocation(new URI(original));
            return new ResponseEntity<>(httpHeaders,HttpStatus.MOVED_PERMANENTLY);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Object> getRequestCnt(@RequestParam String shortenUrl){
        int cnt=urlService.getCnt(shortenUrl);
        if(cnt==-1){
            return new ResponseEntity<>(new FailResponse(false,"not found",404),HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(new GetCntResponse(true,"success",200,cnt), HttpStatus.OK);
        }
    }


}
