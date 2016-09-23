package Chapter_3_Exercises;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Here is a concrete example of a ColorTransformer . We want to put a frame around
 * an image, like this:
 * First, implement a variant of the transform method of Section 3.3, “Choosing
 * a Functional Interface,” on page 50, with a ColorTransformer instead of an
 * UnaryOperator<Color> . Then call it with an appropriate lambda expression to put
 * a 10 pixel gray frame replacing the pixels on the border of an image.
 */

@FunctionalInterface
interface ColorTransformer {
    Color apply(int x, int y, Color color);
}

public class Exercise5 extends Application {

    public static Image transform(Image image, ColorTransformer transformer) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        WritableImage result = new WritableImage(width, height);
        for(int x = 0; x < width; x++)
            for(int y = 0; y < height; y++)
                result.getPixelWriter()
                        .setColor(x, y, transformer.apply(x ,y, image.getPixelReader().getColor(x, y)));
        return result;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Image image = new Image("Chapter_3_Exercises/trinity-college-dublin.png");

        ColorTransformer transformation = (x, y, color) -> {
            if (x <= 10 || x >= image.getWidth() - 10
                    || y <= 10 || y >= image.getHeight() - 10) return Color.GRAY;
            return color;
        };

        primaryStage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(transform(image, transformation)))));
        primaryStage.show();
    }
}