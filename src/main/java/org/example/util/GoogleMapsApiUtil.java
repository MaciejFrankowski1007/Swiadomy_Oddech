package org.example.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class GoogleMapsApiUtil {

    private static final String GOOGLE_MAPS_API_URL = "https://maps.googleapis.com/maps/api/geocode/json";
    private static final String GOOGLE_MAPS_API_KEY = "AIzaSyDOl9J7LET2d0WngD7WNEsn0FIbkR2VzHI";

    public static String getLocationFromAddress(String address) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String url = GOOGLE_MAPS_API_URL + "?address=" + address + "&key=" + GOOGLE_MAPS_API_KEY;
            HttpGet request = new HttpGet(url);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                return EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}