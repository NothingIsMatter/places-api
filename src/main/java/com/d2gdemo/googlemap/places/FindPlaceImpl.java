package com.d2gdemo.googlemap.places;

import com.d2gdemo.googlemap.places.request.PlaceAutocompleteRequest;
import com.d2gdemo.googlemap.places.request.PlaceFindByTextRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;


public class FindPlaceImpl implements FindPlaceObject {
   private URIBuilder builder;
    private HttpGet getReq;
    private HttpClient client;
    private BufferedReader reader;
    private HttpResponse httpResponse;
    private StringBuilder builders;
    private String s;
    private ObjectMapper mapper;
    private JsonNode node;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public JsonNode sendRequest(PlaceAutocompleteRequest request) throws Exception {
        if (request.isStrictbounds())
        builder = new URIBuilder("https://maps.googleapis.com/maps/api/place/autocomplete/json?strictbounds");
        else builder = new URIBuilder("https://maps.googleapis.com/maps/api/place/autocomplete/json");
        builder.addParameter("key", key);
        builder.addParameter("input", request.getInput());
        if (!StringUtils.isEmpty(request.getLocation())) builder.addParameter("location", request.getLocation());
        if (!StringUtils.isEmpty(request.getRadius())) builder.addParameter("radius", request.getRadius());


        getReq= new HttpGet(builder.build());

        client = HttpClientBuilder.create().build();
        httpResponse = client.execute(getReq);

        String prediction = EntityUtils.toString(httpResponse.getEntity());
        mapper = new ObjectMapper();
        JsonNode node =  mapper.readTree(prediction);
        return node;
    }

    @Override
    public JsonNode sendRequest(PlaceFindByTextRequest request) throws Exception {
        builder = new URIBuilder("https://maps.googleapis.com/maps/api/place/findplacefromtext/json");
        builder.addParameter("key", key);
        builder.addParameter("input", request.getInput());
       if (StringUtils.isEmpty(request.getInputtype()))builder.addParameter("inputtype","textquery"); else builder.addParameter("inputtype",request.getInputtype());


        getReq= new HttpGet(builder.build());

        client = HttpClientBuilder.create().build();
        httpResponse = client.execute(getReq);
        mapper = new ObjectMapper();
        String all = EntityUtils.toString(httpResponse.getEntity());
        JsonNode jsonNode = mapper.readTree(all);
        return jsonNode;
    }

    public JsonNode getDetails(String placeID) throws Exception{
        URIBuilder builder = new URIBuilder("https://maps.googleapis.com/maps/api/place/details/json");
        builder.addParameter("placeid",placeID);
        builder.addParameter("key",key);

        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(new HttpGet(builder.build()));


        String details  = EntityUtils.toString(response.getEntity());

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(details);





    }
}
