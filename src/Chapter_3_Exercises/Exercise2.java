package Chapter_3_Exercises;

import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

/**
 * When you use a ReentrantLock , you are required to lock and unlock with the
 * idiom
 * myLock.lock();
 * try {
 * some action
 * } finally {
 * myLock.unlock();
 * }
 * Provide a method withLock so that one can call
 * withLock(myLock, () -> { some action })
 */
public class Exercise2 {

    private static void withLock(ReentrantLock lock, Runnable action) {
        lock.lock();
        try { action.run(); }
        finally { lock.unlock(); }
    }

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);
        withLock(lock, () -> System.out.println(Thread.currentThread().getName()));
    }
}
