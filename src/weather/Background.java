// Background.java
package weather;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.net.URISyntaxException;
import java.time.LocalTime;

public class Background extends StackPane {
    private Image morningImage;
    private Image afternoonImage;
    private Image eveningImage;
    private Image nightImage;

    public Background() throws URISyntaxException {
        // Initialize your images
        morningImage = new Image("file:src/weather/img/morning.jpeg");
        afternoonImage = new Image("file:src/weather/img/afternoon.jpeg");
        eveningImage = new Image("file:src/weather/img/sunset.jpeg");
        nightImage = new Image("file:src/weather/img/night.jpeg");

        // Set the initial background
        setDynamicBackground();
    }

    public Rectangle setDynamicBackground() {
        LocalTime time = LocalTime.now();
        Image currentImage;
        if (time.isAfter(LocalTime.of(6, 0)) && time.isBefore(LocalTime.of(12, 0))) {
            // Morning
            currentImage = morningImage;
        } else if (time.isAfter(LocalTime.of(12, 0)) && time.isBefore(LocalTime.of(18, 0))) {
            // Afternoon
            currentImage = afternoonImage;
        } else if (time.isAfter(LocalTime.of(18, 0)) && time.isBefore(LocalTime.of(21, 0))) {
            // Evening
            currentImage = eveningImage;
        } else {
            // Night
            currentImage = nightImage;
        }
        Rectangle background = new Rectangle(this.getWidth(), this.getHeight());
        background.setFill(new ImagePattern(currentImage));
        return background;
    }
}