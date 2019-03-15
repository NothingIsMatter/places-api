package com.d2gdemo.googlemap.places;

import com.d2gdemo.googlemap.places.request.PlaceAutocompleteRequest;
import com.d2gdemo.googlemap.places.request.PlaceFindByTextRequest;
import com.fasterxml.jackson.databind.JsonNode;

public interface FindPlaceObject {
     JsonNode sendRequest(PlaceAutocompleteRequest request) throws Exception;
     JsonNode sendRequest(PlaceFindByTextRequest request) throws Exception;
    JsonNode getDetails(String placeID) throws Exception;

}
