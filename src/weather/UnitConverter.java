// UnitConverter.java
package weather;

import java.text.DecimalFormat;

/**
 * UnitConverter is a utility class in the Weather App that provides static methods for converting units of measurement.
 * It supports conversion between different units of temperature (Celsius, Fahrenheit) and wind speed (m/s, km/h, mph).
 *
 * The class does not maintain any state and does not need to be instantiated.
 */
public class UnitConverter {
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

    public static String formatDoubleToTwoDecimalPlaces(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(value);
    }
}