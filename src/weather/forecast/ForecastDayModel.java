package weather.forecast;

public class ForecastDayModel {
    private String date;
    private double temperature;
    private double humidity;
    private double windSpeed;
    private String condition;
    private String icon;

    /**
     * ForecastDayModel is a class that represents the weather forecast for a specific day.
     * It stores the date, temperature, humidity, wind speed, weather condition, and an icon representing the weather condition.
     *
     * The class provides getter methods to access these properties but does not provide any setter methods as the properties are intended to be set at the time of object creation and not modified afterwards.
     */
    public ForecastDayModel(String date, double temperature, double humidity, double windSpeed, String condition, String icon) {
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.condition = condition;
        this.icon = icon;
    }

    public String getDate() {
        return date;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public String getCondition() {
        return condition;
    }

    public String getIcon() {
        return icon;
    }
}