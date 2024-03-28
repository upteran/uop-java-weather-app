// ForecastDayView.java
package weather.forecast;

import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Label;
import weather.UnitConverter;

/**
 * ForecastDayView is a JavaFX BorderPane that represents the view for a specific day's weather forecast in the Weather App.
 * It displays the date, temperature, humidity, wind speed, and weather condition for a specific day.
 * The class uses a ForecastDayModel to get the data to display and a ForecastIconView to get the icon representing the weather condition.
 * It also provides methods to update the units of temperature and wind speed displayed.
 */
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
        String temp = UnitConverter.toCelsius(day.getTemperature());
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
            temp = UnitConverter.toCelsius(this.dataModel.getTemperature());
        } else {
            temp = UnitConverter.toFahrenheit(this.dataModel.getTemperature());
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
            speed = UnitConverter.toMilesPerHourFromMetersPerSecond(this.dataModel.getWindSpeed());
        }
        dayLabel.setText("Date: " + this.dataModel.getDate() +
                "\nTemperature: " + this.dataModel.getTemperature() +
                "\nHumidity: " + this.dataModel.getHumidity() +
                "\nWind Speed: " + speed +
                "\nCondition: " + this.dataModel.getCondition());
    }
}