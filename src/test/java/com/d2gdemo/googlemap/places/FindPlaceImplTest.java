package com.d2gdemo.googlemap.places;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;

public class FindPlaceImplTest {

    @Test
    public void sendRequestAutocomplete() throws Exception {
        FindPlaceImpl findPlace = new FindPlaceImpl();
        findPlace.setKey("KEY");
        JsonNode jsonNode = findPlace.getLocation("London");
        System.out.println(jsonNode.get("predictions"));

    }
}