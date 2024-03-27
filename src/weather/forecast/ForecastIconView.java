// ForecastIconView.java
package weather;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ForecastIconView {
    private String iconCode;

    public ForecastIconView(String iconCode) {
        this.iconCode = iconCode;
    }

    public ImageView getForecastIconView() {
        Image image = new Image("http://openweathermap.org/img/w/" + this.iconCode + ".png");
        ImageView imageView = new ImageView(image);
        return imageView;
    }
}