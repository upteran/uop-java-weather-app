package weather.forecast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * ForecastWeekModel is a class that represents the weather forecast for a week.
 * It stores a list of ForecastDayModel objects, each representing the weather forecast for a specific day.
 *
 * The class provides a getter method to access the list of ForecastDayModel objects but does not provide any setter methods as the properties are intended to be set at the time of object creation and not modified afterwards.
 *
 * The constructor of the class takes a JSONObject as an argument, which is the raw forecast data from the OpenWeatherMap API. It parses this data and creates a list of ForecastDayModel objects.
 */
public class ForecastWeekModel {
    private List<ForecastDayModel> forecast;

    public ForecastWeekModel(JSONObject forecastRaw) {
        System.out.println(forecastRaw.toString()); // print the raw JSON response

        this.forecast = new ArrayList<>();
        JSONArray list = forecastRaw.getJSONArray("list");
        for (int i = 0; i < list.length(); i += 4) {
            JSONObject dayForecast = list.getJSONObject(i);
            String date = dayForecast.getString("dt_txt");
            JSONObject main = dayForecast.getJSONObject("main");
            double temperature = main.getDouble("temp");
            double humidity = main.getDouble("humidity");
            JSONObject wind = dayForecast.getJSONObject("wind");
            double windSpeed = wind.getDouble("speed");
            String condition = dayForecast.getJSONArray("weather").getJSONObject(0).getString("main");
            String icon = dayForecast.getJSONArray("weather").getJSONObject(0).getString("icon");
            this.forecast.add(new ForecastDayModel(date, temperature, humidity, windSpeed, condition, icon));
        }
    }

    public List<ForecastDayModel> getForecast() {
        return forecast;
    }
}