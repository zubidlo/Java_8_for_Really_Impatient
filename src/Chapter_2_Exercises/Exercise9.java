package Chapter_2_Exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Join all elements in a Stream<ArrayList<T>> to one ArrayList<T> . Show how to do
 * this with the three forms of reduce .
 */
public class Exercise9 {

    public static <T> List<T> flatten1(Stream<List<T>> stream) {
        return stream.reduce((a,b) -> {
            List<T> result = new ArrayList<T>(a);
            result.addAll(b);
            return result;
        }).orElse(new ArrayList<T>());
    }

    public static <T> List<T> flatten2(Stream<List<T>> stream) {
        return stream.reduce(new ArrayList<T>(),
                (a,b) -> {
                    List<T> result = new ArrayList<T>(a);
                    result.addAll(b);
                    return result;
                });
    }

    public static <T> List<T> flatten3(Stream<List<T>> stream) {
        return stream.flatMap(e -> e.stream()).collect(Collectors.toList());
    }

    public static <T> List<T> flatten4(Stream<List<T>> stream) {
        return stream.reduce(
                new ArrayList<T>(),
                (acc,b) -> { acc.addAll(b); return acc; },
                (a,b) -> { a.addAll(b); return a; }
        );
    }

    public static void main(String[] args) {
        List<List<Integer>> nestedList = Arrays.asList(
                Arrays.asList(1,2,3),
                Arrays.asList(4,5,6),
                Arrays.asList(7,8,9));

        System.out.println(flatten1(nestedList.stream()));
        System.out.println(flatten2(nestedList.stream()));
        System.out.println(flatten3(nestedList.stream()));
        System.out.println(flatten4(nestedList.stream()));
    }
}
