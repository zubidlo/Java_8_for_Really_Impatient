package Chapter_2_Exercises;

import java.util.stream.Stream;

/**
 * Write a call to reduce that can be used to compute the average of a Stream<Double> .
 * Why can’t you simply compute the sum and divide by count() ?
 */
public class Exercise10 {

    public static void main(String[] args) {

        Stream<Double> nums = Stream.of(1.0, 2.0, 3.0, 4.0, 5.0);
        Pair resultPair = nums.reduce(new Pair(0.0, 0.0), Pair::accumulate, Pair::combine);
        if (resultPair.getCount() > 0)
            System.out.println(resultPair.getAcc() / resultPair.getCount());
    }
}

/**
 * Tuple to store (value, count) pair,
 * with accumulate and combine implementation for the reduce arguments
 */
class Pair {
    private Double acc, count;

    Pair(Double acc, Double count) {
        this.acc = acc; this.count = count;
    }

    Pair accumulate(Double value) { return new Pair(acc + value, count + 1); }
    Pair combine(Pair other) { return new Pair(acc + other.acc, count + other.count); }
    Double getAcc() { return acc; }
    Double getCount() { return count; }
}
