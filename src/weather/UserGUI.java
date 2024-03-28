// UserGUI.java
package weather;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import weather.forecast.ForecastDayModel;
import weather.forecast.ForecastDayView;
import weather.forecast.ForecastView;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * UserGUI is a JavaFX Application that serves as the main user interface for the Weather App.
 * It provides a text field for the user to enter a location and buttons to fetch the weather data.
 * The fetched weather data is displayed in the application, and the search history is maintained and displayed as well.
 *
 * The class uses WeatherAPI to fetch the weather data and HistoryModel to maintain the search history.
 *
 * The class also provides options to view short-term forecast and to switch between different units for temperature and wind speed.
 */
public class UserGUI extends Application {
    private WeatherAPI weatherAPI = new WeatherAPI();
    private HistoryModel historyModel;
    private ListView<String> historyView;

    @Override
    public void start(Stage primaryStage) throws URISyntaxException {
        historyModel = new HistoryModel();
        historyView = new ListView<>();

        primaryStage.setTitle("Weather App");

        TextField locationField = new TextField();
        FlowPane forecastPane = new FlowPane();

        // Search button
        Button getWeatherButton = new Button("Get Weather");

        // Short-term forecast button
        Button forecastButton = new Button("Short-term forecast");
        forecastButton.setVisible(false); // Initially hidden

        // Search box
        HBox searchBox = new HBox(locationField, getWeatherButton);
        searchBox.setSpacing(10);
        searchBox.setAlignment(Pos.CENTER);

        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(searchBox);
        mainPane.setPrefWidth(Screen.getPrimary().getBounds().getWidth() * 0.8);

        // Error
        Label errorLabel = new Label();

        final ForecastDayView[] currentDay = new ForecastDayView[1];

        getWeatherButton.setOnAction(e -> {
            try {
                String location = locationField.getText();
                ForecastDayModel day = weatherAPI.getWeatherData(location);
                System.out.println(day.getDate());
                currentDay[0] = new ForecastDayView(day);

                forecastPane.getChildren().clear(); // Clear the forecastPane
                forecastPane.getChildren().add(currentDay[0].getDayLabel());
                FlowPane.setMargin(currentDay[0].getDayLabel(), new Insets(10, 10, 10, 10));

                // Add location to history
                historyModel.addSearch(location); // Save the search input and its timestamp
                updateHistoryView();
                // Show the forecast button
                forecastButton.setVisible(true); // Show the button when the day forecast is shown
            } catch (Exception ex) {
                errorLabel.setText("An error occurred: " + ex.getMessage());
            }
        });

        HBox forecastBox = new HBox(forecastPane, forecastButton); // New HBox
        forecastBox.setSpacing(10);
        forecastBox.setAlignment(Pos.CENTER_RIGHT);

        BorderPane resultPane = new BorderPane(); // New BorderPane
        resultPane.setCenter(forecastBox);
        BorderPane.setMargin(forecastBox, new Insets(0, 10, 0, 10)); // Add left and right margins

        // Checkboxes
        ComboBox<String> tempUnitsBox = new ComboBox<>(FXCollections.observableArrayList("Celsius", "Fahrenheit"));
        tempUnitsBox.setValue("Celsius"); // Default value
        tempUnitsBox.setOnAction(e -> {
            String selectedUnit = tempUnitsBox.getValue();
            currentDay[0].updateTempUnits(selectedUnit);
        });

        ComboBox<String> windSpeedUnitsBox = new ComboBox<>(FXCollections.observableArrayList("Meters", "Miles"));
        windSpeedUnitsBox.setValue("Meters"); // Default value
        windSpeedUnitsBox.setOnAction(e -> {
            String selectedUnit = tempUnitsBox.getValue();
            currentDay[0].updateWindSpeedUnits(selectedUnit);
        });

        HBox unitsBox = new HBox(tempUnitsBox, windSpeedUnitsBox);
        unitsBox.setSpacing(10);
        unitsBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(mainPane, errorLabel, historyView, unitsBox, resultPane);
        vbox.setAlignment(Pos.CENTER); // Center vertically

        // Create a new BorderPane to hold the VBox and the historyView
        BorderPane rootPane = new BorderPane();
        rootPane.setCenter(vbox);
        rootPane.setBottom(historyView); // Add the historyView to the bottom of the BorderPane
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

    private void updateHistoryView() {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Map.Entry<String, LocalDateTime> entry : historyModel.getHistory().entrySet()) {
            items.add(entry.getKey() + " - " + entry.getValue());
        }
        historyView.setItems(items);
    }

    public void display() {
        launch();
    }
}