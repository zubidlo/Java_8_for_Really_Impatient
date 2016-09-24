package Chapter_3_Exercises;

import java.util.*;
import java.util.function.BiFunction;

/**
 * Write a method that generates a Comparator<String> that can be normal or re-
 * versed, case-sensitive or case-insensitive, space-sensitive or space-insensitive,
 * or any combination thereof. Your method should return a lambda expression.
 */
public class Exercise7 {

    private static Comparator<String> comparator(BiFunction<String, String, Integer> howto) {
        return (a, b) -> howto.apply(a, b);
    }

    public static void main(String[] args) {
        String[] words = {"A", " a" , "a", "abc", "cba"};
        // 1.original words
        System.out.println(Arrays.asList(words));

        List<Comparator<String>> comparators = Arrays.asList(
                // 2.normal comparison
                String::compareTo,
                // 3.normal with order reversed
                (a, b) -> b.compareTo(a),
                // 4.lowerCase
                (a, b) -> a.toLowerCase().compareTo(b.toLowerCase()),
                // 5.trimmed spaces
                (a, b) -> a.trim().compareTo(b.trim()),
                // 6.reversed
                (a, b) -> new StringBuffer(a).reverse().toString()
                        .compareTo(new StringBuffer(b).reverse().toString())
        );

        comparators.stream()
                .forEach(comparison -> {
                    Arrays.sort(words, comparison);
                    System.out.println(Arrays.asList(words));
                });
    }
}
