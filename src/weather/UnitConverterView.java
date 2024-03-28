// UnitConverterView.java
package weather;

public class UnitConverterView {
    public static double toCelsius(double temperature) {
        return (temperature - 32) * 5/9;
    }

    public static double toFahrenheit(double temperature) {
        return temperature * 9/5 + 32;
    }

    public static double toKilometersPerHour(double windSpeed) {
        return windSpeed * 3.6;
    }

    public static double toMilesPerHour(double windSpeed) {
        return windSpeed / 1.609;
    }

    public static String formatHumidity(double humidity) {
        return String.format("%.2f%%", humidity);
    }

    public static String translateCondition(String condition) {
        // Add your translation logic here
        return condition;
    }
}