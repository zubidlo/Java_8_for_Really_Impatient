package Chapter_3_Exercises;

import java.util.function.Function;

/**
 * Define a map operation for a class Pair<T> that represents a pair of objects of
 * type T .
 */

class Pair<T> {
    private final T first, second;
    Pair(T f, T s) { first = f; second = s; }
    <S> Pair<S> map(Function<? super T, ? extends S> fun) {
        return new Pair<>(fun.apply(first), fun.apply(second));
    }
    @Override public String toString() {
        return String.format("(%s,%s)", first, second);
    }
}

public class Exercise23 {

    public static void main(String[] args) {
        Pair<String> name = new Pair<>("Martin", "Zuber");

        System.out.print(name + " -> ");
        System.out.println(new Pair<>("Martin", "Zuber").map(String::toUpperCase));

        System.out.print(name + " -> ");
        System.out.println(new Pair<>("Martin", "Zuber").map(a -> new Pair<>(a, a)));
    }
}
