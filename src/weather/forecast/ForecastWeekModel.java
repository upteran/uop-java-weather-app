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
            String icon = dayForecast.getJSONArray("weather").getJSONObject(0).getString("icon");
            this.forecast.add(new ForecastDay(date, temperature, humidity, windSpeed, condition, icon));
        }
    }

    public List<ForecastDay> getForecast() {
        return forecast;
    }
}