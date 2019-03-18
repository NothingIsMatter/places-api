package com.d2gdemo.googlemap;

import com.d2gdemo.googlemap.places.FindPlaceImpl;
import com.d2gdemo.googlemap.places.FindPlaceObject;
import com.d2gdemo.googlemap.places.FindRouteImpl;
import com.d2gdemo.googlemap.places.FindRouteObject;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = "com.d2gdemo.googlemap")
@Configuration
@EnableWebMvc
@EnableJpaRepositories
public class GooglemapApplication {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(GooglemapApplication.class, args);
    }
}
