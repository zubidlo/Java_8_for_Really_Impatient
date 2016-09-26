package Chapter_3_Exercises;

import java.util.concurrent.*;
import java.util.function.Function;

/**
 * Supply a static method <T, U> Future<U> map(Future<T>, Function<T, U>) . Return an
 * object of an anonymous class that implements all methods of the Future
 * interface. In the get methods, invoke the function.
 */
public class Exercise21 {

    private static <T, U> Future<U> map(Future<T> future, Function<T, U> fun) {
        return new Future<U>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return future.cancel(mayInterruptIfRunning);
            }

            @Override
            public boolean isCancelled() {
                return future.isCancelled();
            }

            @Override
            public boolean isDone() {
                return future.isDone();
            }

            @Override
            public U get() throws InterruptedException, ExecutionException {
                return fun.apply(future.get());
            }

            @Override
            public U get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return fun.apply(future.get(timeout, unit));
            }
        };
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> futureSupplier = executorService.submit(() -> "123");
        Future<Integer> futureParser = map(futureSupplier, Integer::parseInt);
        executorService.shutdown();
        System.out.println(futureParser.get());
    }
}
