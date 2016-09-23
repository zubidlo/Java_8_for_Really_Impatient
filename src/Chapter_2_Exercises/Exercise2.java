package Chapter_2_Exercises;

import java.util.stream.Collectors;

import static Chapter_2_Exercises.Exercise.longerThanNCharsWithLogging;
import static Chapter_2_Exercises.Exercise.setOfWords;

/**
 * Verify that asking for the first five long words does not call the filter method
 * once the fifth long word has been found. Simply log each method call.
 */
class Exercise2 {

    public static void main(String[] args) {

        int n = 7;
        setOfWords = setOfWords.stream()
                .filter(longerThanNCharsWithLogging(n, true))
                .limit(5)
                .collect(Collectors.toList());

        System.out.format("first 5 words longer then %d characters: %s%n",
                n, setOfWords);
    }
}
