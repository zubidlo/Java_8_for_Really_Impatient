package Chapter_3_Exercises;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * Enhance the LatentImage class in Section 3.6, “Laziness,” on page 56, so that it
 * supports both UnaryOperator<Color> and ColorTransformer . Hint: Adapt the former
 * to the latter.
 */

class LatentImage {

    private final Image in;
    private final List<ColorTransformer> pendingOperations = new ArrayList<>();

    private LatentImage(final Image image) { in = image; }

    private static ColorTransformer mapToColorTransformer(UnaryOperator<Color> op) {
        return (x, y, color) -> op.apply(color);
    }

    static LatentImage from(final Image image) {
        return new LatentImage(image);
    }

    LatentImage transform(UnaryOperator<Color> f) {
        pendingOperations.add(mapToColorTransformer(f));
        return this;
    }

    LatentImage transform(ColorTransformer t) {
        pendingOperations.add(t);
        return this;
    }

    Image toImage() {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                Color c = in.getPixelReader().getColor(x, y);
                for (ColorTransformer t : pendingOperations) c = t.apply(x, y, c);
                out.getPixelWriter().setColor(x, y, c);
            }
        return out;
    }
}

public class Exercise12 extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        Image image = new Image("Chapter_3_Exercises/trinity-college-dublin.png");

        ColorTransformer addBorder = (x, y, color) ->
                x <= 10
                        || x >= image.getWidth() - 10
                        || y <= 10
                        || y >= image.getHeight() - 10 ? Color.HOTPINK : color;

        Image transformed = LatentImage.from(image)
                .transform(Color::brighter)
                .transform(Color::desaturate)
                .transform(addBorder)
                .toImage();

        primaryStage.setScene(new Scene(
                new HBox(
                        new ImageView(image),
                        new ImageView(transformed)
                        )
                )
        );

        primaryStage.show();
    }
}
