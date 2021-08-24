package com.Topiko.NearPlaces;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;

public class Place {
	 	private String id;
	    private String icon;
	    private String name;
	    private String vicinity;
	    private Double latitude;
	    private Double longitude;
	    private String placeType;
	   // private Boolean openNow;

	   
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getVicinity() {
			return vicinity;
		}
		public void setVicinity(String vicinity) {
			this.vicinity = vicinity;
		}
		public Double getLatitude() {
			return latitude;
		}
		public void setLatitude(Double latitude) {
			this.latitude = latitude;
		}
		public Double getLongitude() {
			return longitude;
		}
		public void setLongitude(Double longitude) {
			this.longitude = longitude;
		}
		
		 @Override
		    public String toString() {
		        return "Place{" + "id=" + id + ", icon=" + icon + ", name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + '}';
		    }
		 
		public static Place jsonObjPointToPlaceObj(JSONObject pontoReferencia)
		    {
		    	try {
		    		Place placeObj = new Place();
		            JSONObject geometry = (JSONObject) pontoReferencia.get("geometry");
		            JSONObject location = (JSONObject) geometry.get("location");
		            BigDecimal  lat = (BigDecimal)location.get("lat");
		            placeObj.setLatitude(lat.doubleValue());
		            BigDecimal  lng = (BigDecimal)location.get("lng");
		            placeObj.setLongitude(lng.doubleValue());
		            placeObj.setIcon(pontoReferencia.getString("icon"));
		            placeObj.setName(pontoReferencia.getString("name"));
		            placeObj.setVicinity(pontoReferencia.getString("vicinity"));
		            /*if(pontoReferencia.get("opening_hours")!=null)
		            {
		            	JSONObject opening_hours = (JSONObject) pontoReferencia.get("opening_hours");
			            placeObj.setOpenNow((Boolean)opening_hours.get("open_now"));
		            }*/
		          // placeObj.setId(pontoReferencia.get("id"));
		            return placeObj;
				} catch (Exception e) {
					Logger.getLogger(Place.class.getName()).log(Level.SEVERE, null, e);
				}
				return null;
		    	
		    }
		public String getPlaceType() {
			return placeType;
		}
		public void setPlaceType(String placeType) {
			this.placeType = placeType;
		}
		/*public Boolean getOpenNow() {
			return openNow;
		}
		public void setOpenNow(Boolean openNow) {
			this.openNow = openNow;
		}*/
		
	
}
