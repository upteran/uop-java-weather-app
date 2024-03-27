// WeatherAPI.java
package weather;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class WeatherAPI {
    private static final String API_KEY = "7096e11ac8913c244bdee20f178c3613";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";

    private double locationLat;
    private double locationLon;

    public WeatherData getWeatherData(String location) {
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

            return new WeatherData(temperature, humidity, windSpeed, condition);
        } catch (IOException e) {
            throw new RuntimeException("Error: Unable to get weather data", e);
        }
    }

    public ForecastModel getShortTermForecast(String location) {
        // https://pro.openweathermap.org/data/2.5/forecast/hourly?lat={lat}&lon={lon}&appid={API key}
        String urlString = "https://api.openweathermap.org/data/2.5/forecast?lat=" + locationLat + "&lon=" + locationLat  + "&appid=" + API_KEY;
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

            // Parse the response body to extract the forecast data
            // This depends on the structure of the response from the OpenWeatherMap API
            String forecast = parseForecast(jsonObject);

            return new ForecastModel(jsonObject);
        } catch (IOException e) {
            throw new RuntimeException("Error: Unable to get forecast data", e);
        }
    }

    private String parseForecast(JSONObject jsonObject) {
        // Placeholder implementation
        // Replace this with the actual code to parse the forecast data from the response body
        return jsonObject.toString();
    }
}