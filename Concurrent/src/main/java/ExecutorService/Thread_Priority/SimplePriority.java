package ExecutorService.Thread_Priority;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimplePriority implements Runnable {
    private int countDown = 5;
    private volatile double d;

    private volatile double a;
    private int pripority;
    private int NO;

    public SimplePriority(int pripority, int number) {
        this.pripority = pripority;
        this.NO = number;
    }

    public String toString() {
        return Thread.currentThread() + ": " + countDown + ", NO. " + this.NO + ", priority: " + Thread.currentThread().getPriority();
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(this.pripority);
        while (true) {
            for (int i = 0; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double) i;

                if (i % 1000 == 0) {
                    a = 1;
                    for (double b = 1; b < 100; b++) {
                        a += Math.pow(a, b);
                    }
                    Thread.yield();
                }
            }
            System.out.println(this);
            if (--countDown == 0) return;
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            es.execute(new SimplePriority(Thread.MIN_PRIORITY, i));
        }
        es.execute(new SimplePriority(Thread.MAX_PRIORITY, -1));
        es.shutdown();
    }
}
