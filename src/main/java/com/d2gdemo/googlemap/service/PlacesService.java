package com.d2gdemo.googlemap.service;

import com.d2gdemo.googlemap.dto.ChargeStation;

public interface PlacesService {
    boolean save(String id,String location);
    ChargeStation get(String id);
    String getChargeStations(String id,String radius);
    String findRouteBetween(String from,String to);
}
