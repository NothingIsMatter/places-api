package com.d2gdemo.googlemap.places;

import com.fasterxml.jackson.databind.JsonNode;

public interface FindPlaceObject {

     JsonNode getLocation(String input,String radius,String location) throws Exception;
     JsonNode getLocation(String input) throws Exception;


}
