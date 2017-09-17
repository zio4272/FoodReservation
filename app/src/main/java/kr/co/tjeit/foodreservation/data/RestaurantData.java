package kr.co.tjeit.foodreservation.data;

import java.io.Serializable;
import java.io.StringReader;

/**
 * Created by ziO on 2017-09-17.
 */

public class RestaurantData implements Serializable {

    private String name;
    private String location;
    private double latitude;
    private double longitude;
    private String callNum;

    public RestaurantData(String name) {
        this.name = name;
    }

    public RestaurantData(String name, String location, double latitude, double longitude, String callNum) {
        this.name = name;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.callNum = callNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCallNum() {
        return callNum;
    }

    public void setCallNum(String callNum) {
        this.callNum = callNum;
    }
}
