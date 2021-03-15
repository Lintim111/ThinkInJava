package threads;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class consumer {
    private static ThreadLocal<String> context = new ThreadLocal<>();
    private static Random random = new Random();

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        threads ths = new threads();
        for (int i = 0; i < 5; i++) {
            es.submit(() -> {
                ths.setA(random.nextInt(10));
                int statica = random.nextInt(10);
                System.out.println("setStaticA in "+Thread.currentThread().getName()+", with value "+statica);
                threads.setStaticA(statica);
                try {
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        es.shutdown();
    }
}
