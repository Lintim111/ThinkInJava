package Atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    private static final int THREADS_CONUT = 20;
    public static int count = 0;

    public static final AtomicInteger atomicIntegerUnsafe = new AtomicInteger(0);
    public static final AtomicInteger atomicIntegerSafe = new AtomicInteger(0);

    public static void increase() {
        count++;
    }
    public static void atomicIncreaseUnfase() {
        atomicIntegerUnsafe.set(atomicIntegerUnsafe.get()+1);
    }

    public static void atomicIncreaseSafe() {
        atomicIntegerSafe.addAndGet(1);
    }


    public static void main(String[] args){
        Thread[] threads = new Thread[THREADS_CONUT];
        for (int i = 0; i < THREADS_CONUT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        increase();
                        atomicIncreaseUnfase();
                        atomicIncreaseSafe();
                    }
                }
            });
            threads[i].start();
        }

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println("result");
        System.out.println(count);
        System.out.println(atomicIntegerUnsafe);
        System.out.println(atomicIntegerSafe);
        System.out.println("exit");
    }

}
