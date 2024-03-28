// UserGUI.java
package weather;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import weather.forecast.ForecastDayModel;
import weather.forecast.ForecastDayView;
import weather.forecast.ForecastIconView;
import weather.forecast.ForecastView;

public class UserGUI extends Application {
    private WeatherAPI weatherAPI = new WeatherAPI();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Weather App");

        TextField locationField = new TextField();
        Button getWeatherButton = new Button("Get Weather");
        FlowPane forecastPane = new FlowPane();

        Button forecastButton = new Button("Short-term forecast");
        forecastButton.setVisible(false); // Initially hidden

        HBox searchBox = new HBox(locationField, getWeatherButton);
        searchBox.setSpacing(10);
        searchBox.setAlignment(Pos.CENTER);

        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(searchBox);
        mainPane.setPrefWidth(Screen.getPrimary().getBounds().getWidth() * 0.8);

        getWeatherButton.setOnAction(e -> {
            String location = locationField.getText();
            ForecastDayModel day = weatherAPI.getWeatherData(location);
            System.out.println(day.getDate());
            Label weatherLabel = new ForecastDayView(day).getDayLabel();

            forecastPane.getChildren().add(weatherLabel);
            FlowPane.setMargin(weatherLabel, new Insets(10, 10, 10, 10));
            forecastButton.setVisible(true); // Show the button when the day forecast is shown
        });

        HBox forecastBox = new HBox(forecastPane, forecastButton); // New HBox
        forecastBox.setSpacing(10);
        forecastBox.setAlignment(Pos.CENTER_RIGHT);

        BorderPane resultPane = new BorderPane(); // New BorderPane
        resultPane.setCenter(forecastBox);
        BorderPane.setMargin(forecastBox, new Insets(0, 10, 0, 10)); // Add left and right margins

        VBox vbox = new VBox(mainPane, resultPane);
        vbox.setAlignment(Pos.CENTER); // Center vertically

        StackPane rootPane = new StackPane(vbox); // Center horizontally
        Scene mainScene = new Scene(rootPane, 600, 900);

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