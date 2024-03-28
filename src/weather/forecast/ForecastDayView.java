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
    private ForecastDayModel dataModel;

    public ForecastDayView(ForecastDayModel day) {
        this.dataModel = day;
        updateDayLabel(day);
        ImageView image = new ForecastIconView(day.getIcon()).getForecastIconView();
        dayLabel.setGraphic(image);
    }

    private void updateDayLabel(ForecastDayModel day) {
        String temp = UnitConverterView.toCelsius(day.getTemperature());
        dayLabel.setText("Date: " + day.getDate() +
                "\nTemperature: " + temp +
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

    public void updateTempUnits(String unitType) {
        String temp;
        if(unitType.equals("Celsius")) {
            temp = UnitConverterView.toCelsius(this.dataModel.getTemperature());
        } else {
            temp = UnitConverterView.toFahrenheit(this.dataModel.getTemperature());
        }
        dayLabel.setText("Date: " + this.dataModel.getDate() +
                "\nTemperature: " + temp +
                "\nHumidity: " + this.dataModel.getHumidity() +
                "\nWind Speed: " + this.dataModel.getWindSpeed() +
                "\nCondition: " + this.dataModel.getCondition());
    }

    public void updateWindSpeedUnits(String unitType) {
        String speed;
        if(unitType.equals("Meters")) {
            speed = "" + this.dataModel.getWindSpeed();
        } else {
            speed = UnitConverterView.toMilesPerHourFromMetersPerSecond(this.dataModel.getWindSpeed());
        }
        dayLabel.setText("Date: " + this.dataModel.getDate() +
                "\nTemperature: " + this.dataModel.getTemperature() +
                "\nHumidity: " + this.dataModel.getHumidity() +
                "\nWind Speed: " + speed +
                "\nCondition: " + this.dataModel.getCondition());
    }
}