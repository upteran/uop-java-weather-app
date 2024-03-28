// WeatherAPI.java
package weather;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;
import weather.forecast.ForecastDayModel;
import weather.forecast.ForecastWeekModel;

/**
 * WeatherAPI is a class that interacts with the OpenWeatherMap API to fetch weather data.
 * It provides methods to get the current weather data for a specific location and to get a short-term forecast for a specific location.
 *
 * The class uses HttpURLConnection to make GET requests to the OpenWeatherMap API and JSONObject to parse the JSON responses.
 */
public class WeatherAPI {
    private static final String API_KEY = "7096e11ac8913c244bdee20f178c3613";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";

    private double locationLat;
    private double locationLon;

    public ForecastDayModel getWeatherData(String location) {
        String urlString = BASE_URL + location + "&appid=" + API_KEY;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();

            JSONObject jsonObject = new JSONObject(result.toString());

            locationLon = jsonObject.getJSONObject("coord").getDouble("lon");
            locationLat = jsonObject.getJSONObject("coord").getDouble("lat");
            double temperature = jsonObject.getJSONObject("main").getDouble("temp");
            double humidity = jsonObject.getJSONObject("main").getDouble("humidity");
            double windSpeed = jsonObject.getJSONObject("wind").getDouble("speed");
            String condition = jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");
            String icon = jsonObject.getJSONArray("weather").getJSONObject(0).getString("icon");

            // Get current date and time
            LocalDateTime now = LocalDateTime.now();

            // Format date and time into a string
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);
            return new ForecastDayModel(formattedDateTime, temperature, humidity, windSpeed, condition, icon);
        } catch (IOException e) {
            throw new RuntimeException("Error: Unable to get weather data", e);
        }
    }

    public ForecastWeekModel getShortTermForecast(String location) {
        // https://pro.openweathermap.org/data/2.5/forecast/hourly?lat={lat}&lon={lon}&appid={API key}
        String urlString = "https://api.openweathermap.org/data/2.5/forecast?lat=" + locationLat + "&lon=" + locationLon  + "&appid=" + API_KEY;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();

            JSONObject jsonObject = new JSONObject(result.toString());

            return new ForecastWeekModel(jsonObject);
        } catch (IOException e) {
            throw new RuntimeException("Error: Unable to get forecast data", e);
        }
    }
}