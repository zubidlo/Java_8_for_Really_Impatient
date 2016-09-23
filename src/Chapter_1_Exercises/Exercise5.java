package Chapter_1_Exercises;

import static Chapter_1_Exercises.Exercise.printCurrentThread;

/**
 * Take a file from one of your projects that contains a number of ActionListener ,
 * Runnable , or the like. Replace them with lambda expressions. How many lines
 * did it save? Was the code easier to read? Were you able to use method
 * references?
 */
public class Exercise5 {

    private static Runnable runnable = () -> printCurrentThread();

    public static void main(String[] args) {
        printCurrentThread();
        new Thread(runnable).start();
    }
}
