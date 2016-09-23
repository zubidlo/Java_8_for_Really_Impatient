package Chapter_2_Exercises;

import java.util.List;
import java.util.function.Supplier;

import static Chapter_2_Exercises.Exercise.setOfWords;
import static Chapter_2_Exercises.Exercise.longerThanNCharsWithLogging;

/**
 * Measure the difference when counting long words with a parallelStream instead
 * of a stream . Call System.currentTimeMillis before and after the call, and print the
 * difference. Switch to a larger document (such as War and Peace) if you have
 * a fast computer.
 */
public class Exercise3 {

    private static long sequentialCount(List<String> words, int chars, boolean withLog) {
        return words.stream()
                .filter(longerThanNCharsWithLogging(chars, withLog))
                .count();
    }

    private static long parallelCount(List<String> words, int chars, boolean withLog) {
        return words.parallelStream()
                .filter(longerThanNCharsWithLogging(chars, withLog))
                .count();
    }

    private static double timeItInMilliseconds(Supplier<Long> count) {
        long startTime = System.nanoTime();
        long words = count.get();
        long time = System.nanoTime() - startTime;
        System.out.println("words found: " + words);
        return time / 1000000;
    }

    public static void main(String[] args) {

        int wordLength = 12;
        boolean withLog = false;
        System.out.println("find all the words longer than " + wordLength + " characters");

        System.out.format("sequentially:%f ms%n",
                timeItInMilliseconds(() -> sequentialCount(setOfWords, wordLength, withLog)));
        System.out.format("in parallel:%f ms%n",
                timeItInMilliseconds(() -> parallelCount(setOfWords, wordLength, withLog)));
    }
}
