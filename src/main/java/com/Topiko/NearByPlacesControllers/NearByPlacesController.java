package com.Topiko.NearByPlacesControllers;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.Topiko.DTOClasses.MobileResponseDTO;
import com.Topiko.NearByPlacesServices.NearByPlacesService;
import com.Topiko.NearPlaces.Place;

@RestController
@RequestMapping(value="/NearByPlaces")
public class NearByPlacesController
{
    public static final Logger logger = LoggerFactory.getLogger(NearByPlacesController.class);
    
    @Autowired private NearByPlacesService nearByPlacesService;
    
    @PostMapping(value="/gettingPlaces")
    public MobileResponseDTO gettingPlacesBasedOnLGLTAndUserSelectedType(@RequestBody Place place)
    {
    	logger.info("inside NearBy Places controller method");
    	MobileResponseDTO resp = new MobileResponseDTO();
    	if (place.getPlaceType().equals("")) {
    		resp.setMessage("Please Give Place Type");
    	}
    	else
    	{
    	resp = nearByPlacesService.findPlaces(place.getLatitude(),place.getLongitude(),place.getPlaceType(),resp);
    	}
        return resp;
    }
}
