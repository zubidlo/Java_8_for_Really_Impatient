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
 * Generalize Exercise 5 by writing a static method that yields a ColorTransformer
 * that adds a frame of arbitrary thickness and color to an image.
 */

public class Exercise8 extends Application{

    private static ColorTransformer borderAdder(Image image, int thickness, Color color) {
        return (x, y, c) ->
            x <= thickness
                    || x >= image.getWidth() - thickness
                    || y <= thickness
                    || y >= image.getHeight() - thickness ? color : c;
    }

    private static Image transform(Image image, ColorTransformer transformer) {

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

        Image orangeBorder = transform(image, borderAdder(image, 10, Color.ORANGE));
        Image pinkBorder = transform(image, borderAdder(image, 20, Color.PINK));

        primaryStage.setScene(
                new Scene(
                        new HBox(
                                new ImageView(image),
                                new ImageView(orangeBorder),
                                new ImageView(pinkBorder))
                )
        );

        primaryStage.show();
    }
}
