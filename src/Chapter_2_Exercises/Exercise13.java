package Chapter_2_Exercises;

import java.util.stream.Collectors;

import static Chapter_2_Exercises.Exercise.setOfWords;

/**
 * Repeat the preceding exercise, but filter out the short strings and use the
 * collect method with Collectors.groupingBy and Collectors.counting .
 */
public class Exercise13 {

    public static void main(String[] args) {

        setOfWords.stream()
                .filter(w -> w.length() < 12)
                .collect(Collectors.groupingBy(String::length, Collectors.counting()))
                .forEach((key, value) -> System.out.format("count of words with length: %d is %d%n", key, value));
    }
}
