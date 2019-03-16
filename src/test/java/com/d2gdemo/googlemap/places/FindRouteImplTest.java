package com.d2gdemo.googlemap.places;

import org.junit.Test;

import static org.junit.Assert.*;

public class FindRouteImplTest {
    @Test
    public void findRoute(){
        FindRouteImpl findRoute = new FindRouteImpl();
        findRoute.setKey("TEST");
        findRoute.findRouteBetween("Berlin","London");

    }

}