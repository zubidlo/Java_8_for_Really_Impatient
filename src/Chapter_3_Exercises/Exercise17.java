package Chapter_3_Exercises;

import java.util.function.Consumer;

/**
 * Implement a doInParallelAsync(Runnable first, Runnable second, Consumer<Throwable>)
 * method that executes first and second in parallel, calling the handler if
 * either method throws an exception.
 */
public class Exercise17 {

    private static void doInParallelAsync(Runnable first,
                                          Runnable second,
                                          Consumer<Throwable> handler) {

        Thread thread = new Thread() {
            @Override
            public void run() {
                new Thread(() -> {
                    try {
                        first.run();
                    } catch (Exception e) {
                        handler.accept(e);
                    }
                }).start();
                new Thread(() -> {
                    try {
                        second.run();
                    } catch (Exception e) {
                        handler.accept(e);
                    }
                }).start();
            }
        };

        thread.start();
    }

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName());

        Runnable first = () -> {
            System.out.println(Thread.currentThread().getName());
            Integer.parseInt("123");
        };

        Runnable second = () -> {
            System.out.println(Thread.currentThread().getName());
            Integer.parseInt("a");
        };

        doInParallelAsync(first, second, t -> System.err.println(t));
    }
}
