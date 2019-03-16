package com.d2gdemo.googlemap.database;

import com.d2gdemo.googlemap.dto.ChargeStation;

public interface ChargerDao {
    boolean save(String id,String location);
    String get(String id);
}
