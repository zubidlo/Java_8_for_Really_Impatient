package Chapter_3_Exercises;

import java.util.concurrent.*;

/**
 * Is there a flatMap operation for CompletableFuture ? If so, what is it?
 */
public class Exercise22 {

    private static void printThread() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        printThread();
        //thenCompose : CF<Int> -> Int -> CF<Int> -> CF<Int>
        // seems like flatMap type
        CompletableFuture.supplyAsync(() -> { printThread(); return 42; })
                .thenCompose(value -> CompletableFuture.supplyAsync(() -> { printThread(); return value - 42; }))
                .thenAccept(value -> System.out.println("42 - 42 = " + value));
    }
}
