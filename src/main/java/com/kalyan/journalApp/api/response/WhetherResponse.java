package com.kalyan.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class WhetherResponse {

    private Current current;



    @Getter
    @Setter
    public class Current{
        @JsonProperty("observation_time")
        private String observationTime;
        private int temperature;
        @JsonProperty("weather_descriptions")
        private ArrayList<String> weather_descriptions;
        private int feelslike;

    }




}
