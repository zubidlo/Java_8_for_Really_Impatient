package Chapter_2_Exercises;

import java.util.stream.Stream;

/**
 * Using Stream.iterate , make an infinite stream of random numbers—not by
 * calling Math.random but by directly implementing a linear congruential generator.
 * In such a generator, you start with x 0 = seed and then produce x n + 1 =
 * (a x n + c) % m, for appropriate values of a, c, and m. You should implement a
 * method with parameters a , c , m , and seed that yields a Stream<Long> . Try out a =
 * 25214903917, c = 11, and m = 2 48 .
 */
public class Exercise5 {

    public static void main(String[] args) {

        long x0 = System.currentTimeMillis();
        long a = 25214903917L;
        long c = 11;
        long m = (long) Math.pow(2, 48);

        Stream<Long> s = Stream.iterate(x0, x -> (a * x + c) % m);
        s.limit(5).forEach(System.out::println);

    }
}
