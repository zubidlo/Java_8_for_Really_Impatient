package Chapter_3_Exercises;

/**
 * To deal with lazy evaluation on a per-pixel basis, change the transformers so
 * that they are passed a PixelReader object from which they can read other pixels
 * in the image. For example, (x, y, reader) -> reader.get(width - x, y) is a mirror-
 * ing operation. The convolution filters from the preceding exercises can be
 * easily implemented in terms of such a reader. The straightforward operations
 * would simply have the form (x, y, reader) -> reader.get(x, y).grayscale() , and
 * you can provide an adapter from UnaryOperation<Color> . A PixelReader is at a
 * particular level in the pipeline of operations. Keep a cache of recently read
 * pixels at each level in the pipeline. If a reader is asked for a pixel, it looks in
 * the cache (or in the original image at level 0); if that fails, it constructs a
 * reader that asks the previous transform.
 */

public class Exercise14 {
}
