package com.kalyan.journalApp.service;

import com.kalyan.journalApp.api.response.WhetherResponse;
import com.kalyan.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WhetherService {

    @Value("${whether_api_key}")
    private  String apiKey;

    private static final String API="https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;





    public WhetherResponse getWhether(String city){
        String finalAPI=appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace("<city>",city).replace("<apiKey>",apiKey);
        ResponseEntity<WhetherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WhetherResponse.class);
        WhetherResponse body =response.getBody();
        return body;

    }
}
