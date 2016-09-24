package Chapter_3_Exercises;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.function.UnaryOperator;
import static Chapter_3_Exercises.Exercise5.transform;

/**
 * Implement static methods that can compose two ColorTransformer objects, and
 * a static method that turns a UnaryOperator<Color> into a ColorTransformer that ig-
 * nores the x- and y-coordinates. Then use these methods to add a gray frame
 * to a brightened image. (See Exercise 5 for the gray frame.)
 */
public class Exercise11 extends Application {

    private static ColorTransformer compose(ColorTransformer first, ColorTransformer second) {
        return (x, y, color) -> second.apply(x, y, first.apply(x , y, color));
    }

    private static ColorTransformer toColorTransformer(UnaryOperator<Color> op) {
        return (x, y, color) -> op.apply(color);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Image image = new Image("Chapter_3_Exercises/trinity-college-dublin.png");

        // grey border
        ColorTransformer borderTransform = (x, y, color) ->
                       x <= 10
                    || x >= image.getWidth() - 10
                    || y <= 10
                    || y >= image.getHeight() - 10 ? Color.GRAY : color;

        primaryStage.setScene(
                new Scene(
                        new HBox(
                                new ImageView(image),
                                new ImageView(transform(image, compose(toColorTransformer(Color::brighter), borderTransform))
                                )
                        )
                )
        );

        primaryStage.show();
    }
}
