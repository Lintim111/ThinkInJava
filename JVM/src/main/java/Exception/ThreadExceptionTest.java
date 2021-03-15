package Exception;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadExceptionTest {
    private ExecutorService ES;

    public ThreadExceptionTest() {
        ES = Executors.newCachedThreadPool();
    }

    public ThreadExceptionTest(int threadCount) {
        ES = Executors.newFixedThreadPool(threadCount);
    }

    public void run(Runnable runnable) throws Exception {
        Future future = ES.submit(runnable);
        //future.get(10, TimeUnit.SECONDS);
    }

    public  Future run(Callable callable) throws Exception {
        Future future = ES.submit(callable);
        //T result = future.get(10, TimeUnit.SECONDS);
        return future;
    }

    public void shotdown() throws InterruptedException {
        ES.shutdown();
        TimeUnit.SECONDS.sleep(1);
        ES.shutdownNow();
        System.out.println("ES shutdown");
    }

    public static void main(String[] args) throws Exception {
        ThreadExceptionTest threadExceptionTest = new ThreadExceptionTest(15);
        for(int i=0;i<5;i++) {
            threadExceptionTest.run(new Runnable() {
                @Override
                public void run() {
                    int a = 0;
                    while (a < 10) {
                        try {
                            Thread.sleep(100);
                            System.out.println("runnable: " + a);
                        } catch (InterruptedException e) {
                            throw new RuntimeException("ws");
                        }
                        a++;

                    }
                }
            });
        }

        List<Future> futures = new ArrayList<>();
        for(int i=0;i<5;i++) {

            futures.add(threadExceptionTest.run(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int b = 100;
                    while (b > 90) {
                        try {
                            Thread.sleep(100);
                            System.out.println("Callable: " + b);
                        } catch (InterruptedException e) {
                            throw new RuntimeException("ws");
                        }
                        b--;
                        if (b == 96) {
                            throw new CustomerException("b is 96");
                        }
                    }
                    return 10;
                }
            }));

            TimeUnit.MILLISECONDS.sleep(20);
            System.out.println("Call cycle, i: "+i);
        }
        TimeUnit.SECONDS.sleep(20);
        threadExceptionTest.shotdown();

        System.out.println("end");
        System.out.println("future 0: "+futures.get(0).get(1,TimeUnit.SECONDS));
    }
}
