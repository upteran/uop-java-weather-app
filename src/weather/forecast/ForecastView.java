// ForecastView.java
package weather;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

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
        ForecastModel forecastModel = weatherAPI.getShortTermForecast(location);
        for (ForecastDay day : forecastModel.getForecast()) {
            Label dayLabel = new Label();
            dayLabel.setText("Date: " + day.getDate() +
                    "\nTemperature: " + day.getTemperature() +
                    "\nHumidity: " + day.getHumidity() +
                    "\nWind Speed: " + day.getWindSpeed() +
                    "\nCondition: " + day.getCondition());

            ImageView image = new ForecastIconView(day.getIcon()).getForecastIconView();
            dayLabel.setGraphic(image);
            forecastPane.getChildren().add(dayLabel);

            FlowPane.setMargin(dayLabel, new Insets(10, 10, 10, 10));
        }
        this.setCenter(forecastPane);
    }
}