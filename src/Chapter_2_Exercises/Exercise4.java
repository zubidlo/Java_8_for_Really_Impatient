package Chapter_2_Exercises;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Suppose you have an array int[] values = { 1, 4, 9, 16 } . What is
 * Stream.of(values) ? How do you get a stream of int instead?
 */
public class Exercise4 {

    public static void main(String[] args) {

        int[] values = { 1, 4, 9, 16 };
        IntStream intStream = Arrays.stream(values);
        intStream.forEachOrdered(System.out::println);
    }
}
