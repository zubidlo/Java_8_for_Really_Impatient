package Chapter_3_Exercises;

import java.util.concurrent.Callable;
import java.util.function.BiConsumer;

/**
 * Implement the doInOrderAsync of Section 3.8, “Dealing with Exceptions,” on
 * page 58, where the second parameter is a BiConsumer<T, Throwable> . Provide
 * a plausible use case. Do you still need the third parameter?
 */
public class Exercise16 {

    static <T> void doInOrderAsync(Callable<T> first, BiConsumer<T, Throwable> second) {
        Thread t = new Thread() {
            public void run() {
                T t;
                try {
                    t = first.call();
                    second.accept(t, null);
                } catch (Exception e) { e.printStackTrace(); }
            }
        };
        t.start();
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        doInOrderAsync(() -> Thread.currentThread().getName(), (s, t) -> System.out.println(s));
    }
}
