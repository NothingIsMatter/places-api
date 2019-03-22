package com.d2gdemo.googlemap.restcontroller;



import com.d2gdemo.googlemap.entity.ChargeStation;
import com.d2gdemo.googlemap.restcontroller.exception.ServerException;
import com.d2gdemo.googlemap.service.PlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("places")
public class Controller {
    @Autowired
    private PlacesService placesService;
    @GetMapping("get")
    public ChargeStation getChargeStation(@RequestParam(name = "id") String id){
     return placesService.get(id);
    }
    @PostMapping("/add")
    public void addChargeStation(@RequestParam(name = "id") String id,@RequestParam(name = "location") String location){
        placesService.save(id,location);
    }
    @GetMapping("chargers")
    public String getChargeStationsForId(@RequestParam(name = "forid") String id,@RequestParam(name = "radius") String radius) throws Exception{
try {
    return placesService.getChargeStations(id,radius);
} catch (Exception ex){
    throw new ServerException("Error during getting charge stations");
}

    }
    @GetMapping("route")
    public String findRouteBetween(@RequestParam("from") String from,@RequestParam("to") String to){
 return placesService.findRouteBetween(from,to);
    }



}
