package com.d2gdemo.googlemap.places.request;

public class PlaceAutocompleteRequest {
    private String input;
    private String location;
    private String radius;
private boolean strictbounds;

    public boolean isStrictbounds() {
        return strictbounds;
    }

    public void setStrictbounds(boolean strictbounds) {
        this.strictbounds = strictbounds;
    }

    public PlaceAutocompleteRequest(String input, String location, String radius,boolean strictbounds) {
        this.input = input;
        this.location = location;
        this.radius = radius;
        this.strictbounds = strictbounds;
    }

    public PlaceAutocompleteRequest(String input) {
        this.input = input;
    }

    public PlaceAutocompleteRequest(String input, String location) {
        this.input = input;
        this.location = location;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }
}
