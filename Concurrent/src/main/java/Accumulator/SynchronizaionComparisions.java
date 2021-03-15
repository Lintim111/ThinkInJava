package Accumulator;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

abstract class Accumulator{
    public static long cycles = 50000L;
    private static final int N=4;
    public static ExecutorService exec = Executors.newFixedThreadPool(N*2);
    private static CyclicBarrier barrier = new CyclicBarrier(N*2+1);

    protected volatile int index = 0;
    protected volatile long value =0;
    protected long duration =0 ;
    protected String id ="error";
    protected final static int SIZE=100000+100;
    protected static int[] preLoaded = new int[SIZE];
    static {
        Random random = new Random(47);
        for(int i=0;i<SIZE;i++){
            preLoaded[i]=random.nextInt();
        }
    }

    public abstract void acculmulate();
    public abstract long read();
    private class Modifer implements Runnable{
        @Override
        public void run() {
            for(long i=0;i<cycles;i++){
                acculmulate();
            }
            try{
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
    private class Reader implements Runnable{

        private volatile long value;
        @Override
        public void run() {
            for(long i=0;i<cycles;i++){
                value=read();
            }
            try{
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
    public void timedTest(){
        long start = System.nanoTime();
        for(int i=0;i<N;i++){
            exec.execute(new Modifer());
            exec.execute(new Reader());
        }
        try{
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        duration = System.nanoTime()-start;
        System.out.println( String.format( "%-13s: %13d\n",id,duration));
    }

    public static void report(Accumulator acc1, Accumulator acc2){
        System.out.println(String.format("%-22s: %.2f\n",acc1.id+"/"+acc2.id,(double)acc1.duration/acc2.duration));
    }
}

class Baseline extends Accumulator{
    {id="Baseline";}

    @Override
    public void acculmulate() {
        value+=preLoaded[index++];
        if(index>=SIZE)index=0;
    }

    @Override
    public long read() {
        return value;
    }
}

class SynchnorizedTest extends Accumulator{
    {id="Synchronized";}

    @Override
    public synchronized void acculmulate() {
        value+=preLoaded[index++];
        if(index>=SIZE)index=0;

    }

    @Override
    public synchronized long read() {
        return value;
    }
}

class LockTest extends Accumulator{
    {id="Lock";}

    private ReentrantLock lock=new ReentrantLock();
    @Override
    public void acculmulate() {
        lock.lock();
        try{
            value+=preLoaded[index++];
            if(index>=SIZE)index=0;
        }finally {
            lock.unlock();
        }

    }

    @Override
    public long read() {
        lock.lock();
        try{
            return value;
        }finally {
            lock.unlock();
        }
    }
}

class AtomicTest extends Accumulator{

    {id="Atomic";}

    private AtomicInteger index=new AtomicInteger(0);
    private AtomicInteger value = new AtomicInteger(0);
    @Override
    public void acculmulate() {
        int i= index.getAndIncrement();
        value.getAndAdd(preLoaded[i]);
        if(++i>=SIZE)
            index.set(0);
    }

    @Override
    public long read() {
        return value.get();
    }
}

public class SynchronizaionComparisions {
    static Baseline baseline = new Baseline();
    static SynchnorizedTest synchnorizedTest = new SynchnorizedTest();
    static LockTest lockTest = new LockTest();
    static AtomicTest atomicTest = new AtomicTest();

    static void test() {
        System.out.println("==================");
        System.out.println(String.format("%-12s: %13d\n", "Cycles", Accumulator.cycles));
        baseline.timedTest();
        synchnorizedTest.timedTest();
        lockTest.timedTest();
        atomicTest.timedTest();
        Accumulator.report(synchnorizedTest, baseline);
        Accumulator.report(lockTest, baseline);
        Accumulator.report(atomicTest, baseline);
        Accumulator.report(synchnorizedTest, lockTest);
        Accumulator.report(synchnorizedTest, atomicTest);
        Accumulator.report(lockTest, atomicTest);
    }

    public static void main(String[] args) {
        int iterations = 5;
        if (args.length > 0) {
            iterations = new Integer(args[0]);
        }
        System.out.println("Warm Up");
        baseline.timedTest();
        for (int i = 0; i < iterations; i++) {
            test();
            Accumulator.cycles *= 2;
        }
        Accumulator.exec.shutdown();
    }
}
