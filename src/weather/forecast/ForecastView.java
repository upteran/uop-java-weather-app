// ForecastView.java
package weather.forecast;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import weather.UnitConverterView;
import weather.WeatherAPI;

public class ForecastView extends BorderPane {
    private WeatherAPI weatherAPI = new WeatherAPI();
    private Label dayLabel = new Label();

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
            updateDayLabel(day);
            ImageView image = new ForecastIconView(day.getIcon()).getForecastIconView();
            dayLabel.setGraphic(image);
            forecastPane.getChildren().add(dayLabel);
            FlowPane.setMargin(dayLabel, new Insets(10, 10, 10, 10));
        }
        this.setCenter(forecastPane);
    }

    private void updateDayLabel(ForecastDayModel day) {
        dayLabel.setText("Date: " + day.getDate() +
                "\nTemperature: " + day.getTemperature() +
                "\nHumidity: " + day.getHumidity() +
                "\nWind Speed: " + day.getWindSpeed() +
                "\nCondition: " + day.getCondition());
    }

    void updateTempUnits(ForecastDayModel day, String unitType) {
        double temp;
        if(unitType.equals("Celsius")) {
            temp = UnitConverterView.toCelsius(day.getTemperature());
        } else {
            temp = day.getTemperature();
        }
        dayLabel.setText("Date: " + day.getDate() +
                "\nTemperature: " + temp +
                "\nHumidity: " + day.getHumidity() +
                "\nWind Speed: " + day.getWindSpeed() +
                "\nCondition: " + day.getCondition());
    }

    void updateWindSpeedUnits(ForecastDayModel day, String unitType) {
        double speed;
        if(unitType.equals("Kilometers")) {
            speed = UnitConverterView.toKilometersPerHour(day.getWindSpeed());
        } else {
            speed = day.getWindSpeed();
        }
        dayLabel.setText("Date: " + day.getDate() +
                "\nTemperature: " + day.getTemperature() +
                "\nHumidity: " + day.getHumidity() +
                "\nWind Speed: " + speed +
                "\nCondition: " + day.getCondition());
    }
}