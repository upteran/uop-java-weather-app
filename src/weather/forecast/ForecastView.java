// ForecastView.java
package weather.forecast;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import weather.WeatherAPI;

/**
 * ForecastView is a JavaFX BorderPane that represents the view for a 5-day weather forecast in the Weather App.
 * It displays the forecast for each day in a FlowPane, with each day's forecast being represented by a ForecastDayView.
 * The class uses a WeatherAPI to get the forecast data and a ForecastDayModel to represent each day's forecast.
 * It also provides a back button to navigate back to the previous view.
 */
public class ForecastView extends BorderPane {
    private WeatherAPI weatherAPI = new WeatherAPI();

    public ForecastView(String location, Button backButton) {
        Label titleLabel = new Label("5-days forecast " + location);
        titleLabel.setFont(new Font("Arial", 20));
        this.setTop(titleLabel);
        this.setLeft(backButton);
        BorderPane.setMargin(titleLabel, new Insets(10, 10, 10, 10));
        BorderPane.setMargin(backButton, new Insets(10, 10, 10, 10));

        FlowPane forecastPane = new FlowPane();
        ForecastWeekModel forecastModel = weatherAPI.getShortTermForecast(location);
        for (ForecastDayModel day : forecastModel.getForecast()) {
            Label dayLabel = new ForecastDayView(day).getDayLabel();
            forecastPane.getChildren().add(dayLabel);
            FlowPane.setMargin(dayLabel, new Insets(10, 10, 10, 10));
        }
        this.setCenter(forecastPane);
    }
}