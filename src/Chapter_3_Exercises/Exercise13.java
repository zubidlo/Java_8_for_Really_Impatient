package Chapter_3_Exercises;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;
import java.util.function.UnaryOperator;

/**
 * Convolution filters such as blur or edge detection compute a pixel from
 * neighboring pixels. To blur an image, replace each color value by the average
 * of itself and its eight neighbors. For edge detection, replace each color value
 * c with 4c – n – e – s – w, where the other colors are those of the pixel to the
 * north, east, south, and west. Note that these cannot be implemented lazily,
 * using the approach of Section 3.6, “Laziness,” on page 56, since they require
 * the image from the previous stage (or at least the neighboring pixels) to have
 * been computed. Enhance the lazy image processing to deal with these opera-
 * tions. Force computation of the previous stage when one of these operators
 * is evaluated.
 */
public class Exercise13 {

}