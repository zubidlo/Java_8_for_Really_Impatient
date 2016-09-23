package Chapter_1_Exercises;

import java.util.ArrayList;
import java.util.List;

import static Chapter_1_Exercises.Exercise.println;
import static Chapter_1_Exercises.Exercise.printCurrentThread;

/**
 * What happens when a lambda expression captures values in an enhanced
 * for loop such as this one?
 * String[] names = { "Peter", "Paul", "Mary" };
 * List<Runnable> runners = new ArrayList<>();
 * for (String name : names)
 * runners.add(() -> System.out.println(name));
 * www.it-ebooks.infoExercises
 * Is it legal? Does each lambda expression capture a different value, or do they
 * all get the last value? What happens if you use a traditional loop for (int i = 0;
 * i < names.length; i++) ?
 */
public class Exercise8 {

    public static void main(String[] args) {
        String[] names = { "Peter", "Paul", "Mary" };
        List<Runnable> runners = new ArrayList<>();

        for (String name : names)
            runners.add(() -> {
                printCurrentThread();
                println(name);
            });

//        Doesn't compile:
//        for (int i = 0; i < names.length; i++)
//            runners.add(() -> println(names[i]));

        for (Runnable r : runners)
            new Thread(r).start();
    }
}
