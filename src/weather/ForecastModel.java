// ForecastModel.java
package weather;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class ForecastDay {
    private String date;
    private double temperature;
    private double humidity;
    private double windSpeed;
    private String condition;

    public ForecastDay(String date, double temperature, double humidity, double windSpeed, String condition) {
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.condition = condition;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}

public class ForecastModel {
    private List<ForecastDay> forecast;

    public ForecastModel(JSONObject forecastRaw) {
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
            this.forecast.add(new ForecastDay(date, temperature, humidity, windSpeed, condition));
        }
    }

    public List<ForecastDay> getForecast() {
        return forecast;
    }
}