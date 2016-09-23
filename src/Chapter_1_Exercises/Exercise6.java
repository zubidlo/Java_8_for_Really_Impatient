package Chapter_1_Exercises;

import static Chapter_1_Exercises.Exercise.printCurrentThread;

/**
 * Didn’t you always hate it that you had to deal with checked exceptions in a
 * Runnable ? Write a method uncheck that catches all checked exceptions and turns
 * them into unchecked exceptions. For example,
 * new Thread(uncheck(
 * () -> { System.out.println("Zzz"); Thread.sleep(1000); })).start();
 * // Look, no catch (InterruptedException)!
 * Hint: Define an interface RunnableEx whose run method may throw any excep-
 * tions. Then implement public static Runnable uncheck(RunnableEx runner) . Use a
 * lambda expression inside the uncheck function.
 */

@FunctionalInterface
interface RunnableWithException {
    void run() throws Exception;
}

public class Exercise6 {

    public static Runnable uncheck(RunnableWithException runner) {
        return () -> {
            try { runner.run(); }
            catch (Exception e) { e.printStackTrace(); }
        };
    }

    public static void main(String[] args) {
        printCurrentThread();
        new Thread(uncheck(() -> printCurrentThread())).start();
    }
}
