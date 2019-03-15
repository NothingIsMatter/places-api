package com.d2gdemo.googlemap.places;

import com.d2gdemo.googlemap.places.request.PlaceAutocompleteRequest;
import com.d2gdemo.googlemap.places.request.PlaceFindByTextRequest;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;

public class FindPlaceImplTest {

    @Test
    public void sendRequestAutocomplete() throws Exception {
        FindPlaceImpl findPlace = new FindPlaceImpl();
        findPlace.setKey("API_KEY");
        JsonNode jsonNode = findPlace.sendRequest(new PlaceAutocompleteRequest("Norway"));
        System.out.println(jsonNode.get("predictions"));

    }
    @Test
    public void sendRequestTextQuery() throws Exception {
        FindPlaceImpl findPlace = new FindPlaceImpl();
        findPlace.setKey("API_KEY");
        JsonNode jsonNode = findPlace.sendRequest(new PlaceFindByTextRequest("Norway"));
        for (JsonNode node : jsonNode.get("candidates")){
            System.out.println(node);
        }

    }
}