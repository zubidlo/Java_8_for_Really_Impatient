package Chapter_3_Exercises;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/**
 * Java 1.4 added assertions to the language, with an assert keyword. Why were
 * assertions not supplied as a library feature? Could they be implemented as
 * a library feature in Java 8?
 */
public class Exercise3 {

    private static void assertTest(Supplier<Boolean> test) {
        if (!test.get()) throw new AssertionError();
    }

    public static void main(String[] args) {
        assertTest(() -> 1 > 0);
        assertTest(() -> -1 > 0);
    }
}
