// UnitConverterView.java
package weather;

import java.text.DecimalFormat;

public class UnitConverterView {
    public static String toCelsius(double temperature) {
        return formatDoubleToTwoDecimalPlaces(temperature - 273.15);
    }

    public static String toFahrenheit(double temperature) {
        return formatDoubleToTwoDecimalPlaces((temperature - 273.15) * 9/5 + 32);
    }

    public static String toMilesPerHourFromMetersPerSecond(double speed) {
        double convertedSpeed = speed * 2.23694; // Conversion factor
        return formatDoubleToTwoDecimalPlaces(convertedSpeed);
    }

    public static String toMetersPerSecondFromMilesPerHour(double speed) {
        double convertedSpeed = speed / 2.23694; // Conversion factor
        return formatDoubleToTwoDecimalPlaces(convertedSpeed);
    }

    public static String formatHumidity(double humidity) {
        return String.format("%.2f%%", humidity);
    }

    public static String translateCondition(String condition) {
        // Add your translation logic here
        return condition;
    }

    public static String formatDoubleToTwoDecimalPlaces(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(value);
    }
}