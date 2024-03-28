// ForecastDayView.java
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

public class ForecastDayView extends BorderPane {
    private Label dayLabel = new Label();
    private FlowPane pane = new FlowPane();

    public ForecastDayView(ForecastDayModel day) {
        updateDayLabel(day);
        ImageView image = new ForecastIconView(day.getIcon()).getForecastIconView();
        dayLabel.setGraphic(image);
    }

    private void updateDayLabel(ForecastDayModel day) {
        dayLabel.setText("Date: " + day.getDate() +
                "\nTemperature: " + day.getTemperature() +
                "\nHumidity: " + day.getHumidity() +
                "\nWind Speed: " + day.getWindSpeed() +
                "\nCondition: " + day.getCondition());
    }

    public Label getDayLabel() {
        return this.dayLabel;
    }

    public FlowPane getPane() {
        return this.pane;
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