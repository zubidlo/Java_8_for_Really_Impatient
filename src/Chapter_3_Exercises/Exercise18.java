package Chapter_3_Exercises;

import java.util.function.Function;

/**
 * Implement a version of the unchecked method in Section 3.8, “Dealing with
 * Exceptions,” on page 58, that generates a Function<T, U> from a lambda that
 * throws checked exceptions. Note that you will need to find or provide a
 * functional interface whose abstract method throws arbitrary exceptions.
 */
public class Exercise18 {

    @FunctionalInterface
    private interface FunctionWithException<T, U> {
        U apply(T t) throws Exception;
    }

    private static <T, U> Function<T, U> unchecked(FunctionWithException<T, U> f) {
        return t -> {
            try { return f.apply(t); }
            catch (Exception e) { throw new RuntimeException(e); }
        };
    }

    public static void main(String[] args) {
        Function<String, Integer> fun = unchecked(Integer::parseInt);
        fun.apply("123");
    }
}
