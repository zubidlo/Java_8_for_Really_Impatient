package Chapter_3_Exercises;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Supply a static method <T, U> List<U> map(List<T>, Function<T, U>)
 */
public class Exercise20 {

    private static <T, U> List<U> map(List<T> xs, Function<T,U> fun) {
        return xs.stream()
                .map(fun)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(map(Arrays.asList("1","2","3", "4"), Integer::parseInt));
    }
}
