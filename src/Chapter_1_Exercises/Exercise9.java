package Chapter_1_Exercises;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static Chapter_1_Exercises.Exercise.println;

/**
 * Form a subclass Collection2 from Collection and add a default method void
 * forEachIf(Consumer<T> action, Predicate<T> filter) that applies action to each
 * element for which filter returns true . How could you use it?
 */
public class Exercise9 {

    public static void main(String[] args) {
        Collection2<String> names = new Collection2<>();
        names.add("Martin"); names.add("Tomas");
        names.forEachIf(n -> println(n), n -> n.startsWith("T"));
    }
}

class Collection2<T> extends ArrayList<T> {

    void forEachIf(Consumer<T> action, Predicate<T> filter) {
        forEach(t -> { if (filter.test(t)) action.accept(t); });
    }
}