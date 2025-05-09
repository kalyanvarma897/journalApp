package com.kalyan.journalApp.service;

import com.kalyan.journalApp.api.response.WhetherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WhetherService {

    private static final String apiKey="1a497801c01d8c43bc6063c0a8ec5611";
    private static final String API="https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;



    public WhetherResponse getWhether(String city){
        String finalAPI=API.replace("CITY",city).replace("API_KEY",apiKey);
        ResponseEntity<WhetherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WhetherResponse.class);
        WhetherResponse body =response.getBody();
        return body;

    }
}
