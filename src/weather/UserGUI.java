// UserGUI.java
package weather;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserGUI extends Application {
    private WeatherAPI weatherAPI = new WeatherAPI();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Weather App");

        TextField locationField = new TextField();
        Button getWeatherButton = new Button("Get Weather");
        Label weatherLabel = new Label();

        getWeatherButton.setOnAction(e -> {
            String location = locationField.getText();
            WeatherData weatherData = weatherAPI.getWeatherData(location);
            weatherLabel.setText("Temperature: " + weatherData.getTemperature() +
                    "\nHumidity: " + weatherData.getHumidity() +
                    "\nWind Speed: " + weatherData.getWindSpeed() +
                    "\nCondition: " + weatherData.getCondition());
        });

        Button forecastButton = new Button("Short-term forecast");
        VBox vbox = new VBox(locationField, getWeatherButton, weatherLabel, forecastButton);
        Scene mainScene = new Scene(vbox, 600, 900);

        forecastButton.setOnAction(e -> {
            Button backButton = new Button("Back");
            ForecastView forecastView = new ForecastView(locationField.getText(), backButton);
            backButton.setOnAction(event -> primaryStage.setScene(mainScene));
            Scene forecastScene = new Scene(forecastView, 600, 900);
            primaryStage.setScene(forecastScene);
        });

        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public void display() {
        launch();
    }
}