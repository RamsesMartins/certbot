package com.h1.centralvendas.services;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestAPI {
    
    public <T, R> R postForEntity(String url, T requestBody, Class<R> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<T> requestEntity = new HttpEntity<>(requestBody, headers);

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        restTemplate.getMessageConverters().add(converter);

        ResponseEntity<R> responseEntity = restTemplate.postForEntity(url, requestEntity, responseType);
        return responseEntity.getBody();
    }

    public <T, R> R getForEntity(String url, T requestBody, Class<R> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<T> requestEntity = new HttpEntity<>(requestBody, headers);
        
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        restTemplate.getMessageConverters().add(converter);

        ResponseEntity<R> responseEntity = restTemplate.postForEntity(url, requestEntity, responseType);
        return responseEntity.getBody();
    }
}
