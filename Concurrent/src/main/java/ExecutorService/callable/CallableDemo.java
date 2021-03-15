package ExecutorService.callable;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

class TaskWithResult implements Callable<String> {
    private static Random random = new Random(47);

    private int id;
    public TaskWithResult(int id){
        this.id=id;
    }

    @Override
    public String call() throws Exception {
        int sleepTime = random.nextInt(100)+1;
        TimeUnit.MILLISECONDS.sleep(sleepTime);
        return "result of TaskWithResult is "+ this.id+", sleep "+sleepTime;
    }
}

public class CallableDemo {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();
        for (int i = 0; i < 10; i++) {
            results.add(executorService.submit(new TaskWithResult(i)));
        }
        long start = System.currentTimeMillis();
        TimeUnit.MILLISECONDS.sleep(10);
        for (Future<String> fs : results) {
            System.out.println( "is done: "+fs.isDone());
        }

        System.out.println("check finished at " + (System.currentTimeMillis() - start));

        for (Future<String> fs : results) {
            try {
                System.out.println("try at " + (System.currentTimeMillis() - start));
                // get() blocks until completion
                // 按照数组中对象顺序获取, 若当前future对象未完成则阻塞即使后1个已完成
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                executorService.shutdown();
            }
        }
        System.out.println("after gets future");
    }
}
