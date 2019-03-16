package com.d2gdemo.googlemap.places;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class FindPlaceImplTest {

    @Test
    public void sendRequestAutocomplete() throws Exception {
        FindPlaceImpl findPlace = new FindPlaceImpl();
        findPlace.setKey("TEST");
        System.out.println(findPlace.getChargeStations("50000","52.88,13.13"));


    }
    @Test
    public void testRestApi() throws Exception{
        HttpHeaders httpHeaders = new  HttpHeaders();
httpHeaders.add("Accept","application/json");
        RestTemplate restTemplate = new RestTemplate();
        org.springframework.http.HttpEntity entit = new org.springframework.http.HttpEntity(httpHeaders);
    }
}