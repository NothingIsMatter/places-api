package com.d2gdemo.googlemap;

import com.d2gdemo.googlemap.places.FindPlaceImpl;
import com.d2gdemo.googlemap.places.FindPlaceObject;
import com.d2gdemo.googlemap.places.FindRouteImpl;
import com.d2gdemo.googlemap.places.FindRouteObject;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Configuration
@EnableWebMvc

public class GooglemapApplication {

    @Bean
    public FindRouteObject findRouteObject(@Value("${google.secret.key}") String key){
        FindRouteImpl findRoute = new FindRouteImpl();
        findRoute.setKey(key);
        return findRoute;

    }
    @Bean
    public FindPlaceObject findPlaceObject(@Value("${google.secret.key}") String key) throws Exception{
        FindPlaceImpl findPlace = new FindPlaceImpl();
        System.out.println(key);
        findPlace.setKey(key);
        return findPlace;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(GooglemapApplication.class, args);
    }
}
