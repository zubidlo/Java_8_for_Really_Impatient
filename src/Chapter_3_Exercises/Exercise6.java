package Chapter_3_Exercises;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.function.BiFunction;

/**
 * Complete the method
 * public static <T> Image transform(Image in, BiFunction<Color, T, Color> f, T arg)
 * from Section 3.4, “Returning Functions,” on page 53.
 */
public class Exercise6 extends Application{

    public static <T> Image transform(Image in, BiFunction<Color, T, Color> f, T arg) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y,
                        f.apply(in.getPixelReader().getColor(x, y), arg));
            }
        }
        return out;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Image image = new Image("Chapter_3_Exercises/trinity-college-dublin.png");

        //makes the pixels half the bright by modifying the brightness factor
        Image transformed = transform(image, (c, fac) -> c.deriveColor(0, 1, fac, 1), 0.5);

        primaryStage.setScene(
                new Scene(
                        new HBox(
                                new ImageView(image),
                                new ImageView(transformed))
                )
        );

        primaryStage.show();
    }
}
