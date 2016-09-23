package Chapter_1_Exercises;

import static Chapter_1_Exercises.Exercise.printCurrentThread;

/**
 * Write a static method andThen that takes as parameters two Runnable instances
 * and returns a Runnable that runs the first, then the second. In the main method,
 * pass two lambda expressions into a call to andThen , and run the returned
 * instance.
 */
public class Exercise7 {

    private static Runnable andThen(Runnable first, Runnable second) {
        return () -> {
            printCurrentThread();
            new Thread(first).start();
            new Thread(second).start();
        };
    }

    public static void main(String[] args) {
        printCurrentThread();
        new Thread(andThen(() -> printCurrentThread(), () -> printCurrentThread())).start();
    }
}
