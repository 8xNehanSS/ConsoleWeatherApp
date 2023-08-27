
/*
 * Copyright (c) 2023 Nehan Sudasinghe
 *
 */

package org.example;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    static String APIKEY = " "; //api key from https://www.weatherapi.com
    public static void main(String[] args) {
        String separator = "+----------------------------------+";
        System.out.println(separator);
        System.out.println("|           Weather Data           |");
        System.out.println(separator);
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nEnter the city name (99 to EXIT APP): ");
            String location = scanner.next();
            if(location.equals("99")) {
                System.out.println("Exiting app..");
                System.exit(0);
            }
            System.out.println("\nLoading Data..\n");
            CheckWeather(location);
        }
    }

    public static void CheckWeather(String location) {
        try {
            URL url = new URL("http://api.weatherapi.com/v1/current.json?key=" + APIKEY + "&q="+ location +"&aqi=no");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("accept", "application/json");

            InputStream responseStream = connection.getInputStream();

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            WeatherDATA data = mapper.readValue(responseStream, WeatherDATA.class);


            String separator = "+----------------------------------+";

            System.out.println(separator);
            System.out.println("|           Weather Data           |");
            System.out.println(separator);
            System.out.println("| Condition:         " + data.current.condition.text);
            System.out.println("| Temperature:       " + data.current.temp_c + " °C");
            System.out.println("| City Name:         " + data.location.name);
            System.out.println("| Country:           " + data.location.country);
            System.out.println("| Region:            " + data.location.region);
            System.out.println("| Timezone:          " + data.location.tz_id);
            System.out.println("| Local Time:        " + data.location.localtime);
            System.out.println(separator);



        } catch (Exception e) {
            System.out.println("Location not found. Try Again!");
        }
    }
}