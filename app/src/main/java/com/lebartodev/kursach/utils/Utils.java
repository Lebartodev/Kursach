package com.lebartodev.kursach.utils;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Александр on 09.02.2017.
 */

public class Utils {
    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
    public static double getDistance(double lat, double lon, double lat_user, double lon_user) {

        Location locationA = new Location("point A");

        locationA.setLatitude(lat);
        locationA.setLongitude(lon);

        Location locationB = new Location("point B");

        locationB.setLatitude(lat_user);
        locationB.setLongitude(lon_user);

        double distance = locationA.distanceTo(locationB);
        return distance/1000;

    }
    public static String readableDate(long time) {

        if ((time) / (60 * 60 * 24) > 0) {
            return String.valueOf((time) / (60 * 60 * 24)) + " day(s) ago";
        } else if ((time) / (60 * 60) > 0) {
            return String.valueOf((time) / (60 * 60)) + " hour(s) ago";
        } else if ((time) / (60) > 0) {
            return String.valueOf((time) / (60)) + " minutes(s) ago";
        } else
            return "Just now";


    }
    public static String getLocationName(Context context,double lat,double lng){
        LocationManager locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        String provider = locationManager.getBestProvider(new Criteria(), true);


        List<String> providerList = locationManager.getAllProviders();
        if(null!=providerList && providerList.size()>0){
            Geocoder geocoder = new Geocoder(context.getApplicationContext(), Locale.getDefault());
            try {
                List<Address> listAddresses = geocoder.getFromLocation(lat, lng, 1);
                if(null!=listAddresses&&listAddresses.size()>0){
                    return listAddresses.get(0).getAddressLine(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return "";
    }
}
