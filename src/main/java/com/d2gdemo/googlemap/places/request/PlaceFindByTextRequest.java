package com.d2gdemo.googlemap.places.request;

public class PlaceFindByTextRequest {
    private String input;
    private String inputtype;

    public PlaceFindByTextRequest(String input) {
        this.input = input;
    }

    public PlaceFindByTextRequest(String input, String inputtype) {
        this.input = input;
        this.inputtype = inputtype;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getInputtype() {
        return inputtype;
    }

    public void setInputtype(String inputtype) {
        this.inputtype = inputtype;
    }
}
