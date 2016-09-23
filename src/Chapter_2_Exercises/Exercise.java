package Chapter_2_Exercises;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by martin on 20/09/16.
 */
public class Exercise {

    public static List<String> setOfWords;

    static {
         try {
             Path path = Paths.get("src/Chapter_2_Exercises/text.txt");
             setOfWords = Files.lines(path)
                     .flatMap(l -> Arrays.stream(l.split("\\b")))
                     .map(String::toLowerCase)
                     .filter(w -> w.matches("^[a-zA-Z0-9]*$") && w.length() > 0)
                     .distinct()
                     .collect(Collectors.toList());
         } catch (Exception e) {
             e.printStackTrace();
         }
    }

    /**
     * Function: int -> (String -> boolean)
     * @param n if String has more than n chars this function returns true
     * @param withLog if withLog is true, the logging is enabled
     * @return Predicate
     */
    public static Predicate<String> longerThanNCharsWithLogging(int n, boolean withLog) {
        return w -> {
            if (withLog) {
                System.out.format("checking if the word \'%s\' is longer than %d characters in thread:", w, n);
                System.out.println(Thread.currentThread().getName());
            }
            return w.length() > n;
        };
    }

    public static void main(String[] args) {
        System.out.println(setOfWords);
    }
}
