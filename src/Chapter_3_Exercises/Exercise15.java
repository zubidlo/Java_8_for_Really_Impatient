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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;


/**
 * Combine the lazy evaluation of Section 3.6, “Laziness,” on page 56, with the
 * parallel evaluation of Section 3.7, “Parallelizing Operations,” on page 57.
 */

class ParallelLatentImage {

    private final Image in;
    private final List<ColorTransformer> pendingOperations = new ArrayList<>();

    private ParallelLatentImage(final Image image) { in = image; }

    private static ColorTransformer mapToColorTransformer(UnaryOperator<Color> op) {
        return (x, y, color) -> op.apply(color);
    }

    static ParallelLatentImage from(Image image) {
        return new ParallelLatentImage(image);
    }

    ParallelLatentImage transform(UnaryOperator<Color> f) {
        pendingOperations.add(mapToColorTransformer(f));
        return this;
    }

    ParallelLatentImage transform(ColorTransformer t) {
        pendingOperations.add(t);
        return this;
    }

    private Color[][] toPixelArray() {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        Color[][] pixels = new Color[width][height];

        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                pixels[x][y] = in.getPixelReader().getColor(x, y);

        return pixels;
    }

    private Image toImage(Color[][] pixels) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);

        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                out.getPixelWriter().setColor(x, y, pixels[x][y]);

        return out;
    }

    Image toImage() {
        int n = Runtime.getRuntime().availableProcessors();
        Color[][] in = toPixelArray();
        int width = in.length;
        int height = in[0].length;
        Color[][] out = new Color[width][height];
        try {
            ExecutorService pool = Executors.newCachedThreadPool();
            for (int i = 0; i < n; i++) {
                int fromY = i * width / n;
                int toY = (i + 1) * height / n;
                pool.submit(() -> {
                    for (int x = 0; x < width; x++)
                        for (int y = fromY; y < toY; y++)
                            for (ColorTransformer t : pendingOperations)
                                out[x][y] = t.apply(x, y, in[x][y]);
                });
            }
            pool.shutdown();
            pool.awaitTermination(1, TimeUnit.HOURS);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return toImage(out);
    }

}
public class Exercise15 extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        Image image = new Image("Chapter_3_Exercises/trinity-college-dublin.png");

        ColorTransformer addBorder = (x, y, color) ->
                x <= 1
                        || x >= image.getWidth() - 1
                        || y <= 1
                        || y >= image.getHeight() - 1 ? Color.HOTPINK : color;

        Image transformed = ParallelLatentImage.from(image)
                .transform(Color::darker)
                .transform(Color::darker)
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
