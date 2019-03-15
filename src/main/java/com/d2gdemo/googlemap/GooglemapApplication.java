package com.d2gdemo.googlemap;

import com.d2gdemo.googlemap.places.FindPlaceImpl;
import com.d2gdemo.googlemap.places.FindPlaceObject;
import com.d2gdemo.googlemap.places.request.PlaceAutocompleteRequest;
import com.d2gdemo.googlemap.places.request.PlaceFindByTextRequest;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class GooglemapApplication {

    @Bean
    public FindPlaceObject findPlaceObject(@Value("${google.secret.key}") String key){
        FindPlaceImpl findPlace = new FindPlaceImpl();
        System.out.println(key);
        findPlace.setKey(key);
        return findPlace;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(GooglemapApplication.class, args);
    }
}
