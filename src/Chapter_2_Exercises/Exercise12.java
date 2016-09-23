package Chapter_2_Exercises;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static Chapter_2_Exercises.Exercise.setOfWords;

/**
 * Count all short words in a parallel Stream<String> , as described in Section 2.13,
 * “Parallel Streams,” on page 40, by updating an array of AtomicInteger . Use
 * the atomic getAndIncrement method to safely increment each counter.
 */
public class Exercise12 {

    public static void main(String[] args) {

        Stream<String> words = setOfWords.stream();
        AtomicIntegerArray lengths = new AtomicIntegerArray(12);

        words.filter(w -> w.length() < 12)
                .mapToInt(String::length)
                .forEach(l -> lengths.getAndAdd(l, 1));

       IntStream.range(0, 12).forEach(i ->
               System.out.format("count of words with length: %d is %d%n"
                       , i, lengths.get(i)));
    }
}
