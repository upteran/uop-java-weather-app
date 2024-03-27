// UserGUI.java
package weather;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
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
        FlowPane forecastPane = new FlowPane();

        getWeatherButton.setOnAction(e -> {
            String location = locationField.getText();
            ForecastDay day = weatherAPI.getWeatherData(location);
            System.out.println(day.getDate());
            weatherLabel.setText("Date: " + day.getDate() +
                    "\nTemperature: " + day.getTemperature() +
                    "\nHumidity: " + day.getHumidity() +
                    "\nWind Speed: " + day.getWindSpeed() +
                    "\nCondition: " + day.getCondition());

            ImageView image = new ForecastIconView(day.getIcon()).getForecastIconView();
            weatherLabel.setGraphic(image);

            if (forecastPane.getChildren().contains(weatherLabel)) {
                forecastPane.getChildren().remove(weatherLabel);
            }
            forecastPane.getChildren().add(weatherLabel);

            FlowPane.setMargin(weatherLabel, new Insets(10, 10, 10, 10));
        });

        Button forecastButton = new Button("Short-term forecast");
        VBox vbox = new VBox(locationField, getWeatherButton, forecastPane, forecastButton);
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