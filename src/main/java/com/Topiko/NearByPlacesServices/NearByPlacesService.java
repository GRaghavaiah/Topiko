package com.Topiko.NearByPlacesServices;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Topiko.DTOClasses.MobileResponseDTO;
import com.Topiko.NearPlaces.Place;

@Service
public class NearByPlacesService
{
    public static final Logger logger = LoggerFactory.getLogger(NearByPlacesService.class);
    
    public static final String API_KEY="AIzaSyBW0TeLuYYvzuzEGXF53uxTEJ6PIcaF89w";
	public MobileResponseDTO findPlaces(double latitude, double longitude, String placeSpacification,MobileResponseDTO resp)
	{
		String urlString = makeUrl(latitude, longitude,placeSpacification);
		JSONObject jsonObject = new JSONObject();
		logger.info("Type --->"+placeSpacification);
        try {
            String json = getJSON(urlString);

            logger.info("json---->"+json);
            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray("results");

            ArrayList<Place> arrayList = new ArrayList<Place>();
            for (int i = 0; i < array.length(); i++) {
                try {
                    Place place = Place.jsonObjPointToPlaceObj((JSONObject) array.get(i));
                    arrayList.add(place);
                } catch (Exception e) {
                }
            }
            jsonObject.put(placeSpacification, arrayList);
			resp.setMessage(HttpStatus.OK.toString());
			resp.setStatusCode(HttpStatus.OK.toString());
			resp.setRespData(jsonObject.toString());
			return resp;
        } catch (Exception ex) {
        	logger.info("exception in getting places in service---->"+Level.SEVERE, null, ex);
			resp.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			return resp;
        }
	}

	//https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=28.632808,77.218276&radius=500&types=atm&sensor=false&key=api key
    private String makeUrl(double latitude, double longitude,String place) {
         StringBuilder urlString = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");

        if (place.equals("")) {
                urlString.append("&location=");
                urlString.append(Double.toString(latitude));
                urlString.append(",");
                urlString.append(Double.toString(longitude));
                urlString.append("&radius=1500");
             //   urlString.append("&types="+place);
                urlString.append("&sensor=true&key=" + API_KEY);
        } else {
                urlString.append("&location=");
                urlString.append(Double.toString(latitude));
                urlString.append(",");
                urlString.append(Double.toString(longitude));
                urlString.append("&radius=1500");
                urlString.append("&types="+place);
                //urlString.append("&keyword="+cruise);
                urlString.append("&sensor=true&key=" + API_KEY);
        }
        return urlString.toString();
    }

    protected String getJSON(String url) {
        return getUrlContents(url);
    }
    private String getUrlContents(String theUrl) 
    {
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()), 8);
            String line;
            while ((line = bufferedReader.readLine()) != null) 
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return content.toString();
    }
    
    
	
}
