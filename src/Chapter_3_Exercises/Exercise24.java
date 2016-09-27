package Chapter_3_Exercises;

import java.util.function.Function;

/**
 * Can you define a flatMap method for Pair<T> ? If so, what is it? If not, why not?
 */

class PairWithFlatMap<T> {

    private final T first, second;

    PairWithFlatMap(T f, T s) {
        first = f;
        second = s;
    }

    <S> PairWithFlatMap<S> flatMap(Function<? super T, PairWithFlatMap<? extends S>> fun) {
        return new PairWithFlatMap<>(fun.apply(first).first, fun.apply(second).second);
    }

    @Override
    public String toString() {
        return String.format("(%s,%s)", first, second);
    }
}

public class Exercise24 {

    public static void main(String[] args) {
        PairWithFlatMap<String> p = new PairWithFlatMap<>("1", "2");
        System.out.println(
                p.flatMap(a ->
                        new PairWithFlatMap<>(Integer.parseInt(a + "1"), Integer.parseInt(a) + 1
                        )
                )
        );
    }
}
