package Synchronizer.latch;

import java.util.concurrent.CountDownLatch;

public class Pic {
    private String picInfo;
    private CountDownLatch latch = new CountDownLatch(40);//有多少个线程就实例化几个Latch

    public Pic(String picInfo) {
        this.picInfo = picInfo;
    }

    public void doPic(int totalJob) {
        latch = new CountDownLatch(totalJob);
        for(int i=0;i<totalJob;i++) {
            new doPicThread(latch, 1).start();//需要把相同的latch传入到线程类中
        }
        try {
            latch.await();//主线程调用latch的await方法，这个方法会让当前线程中止往下执行，直到每个线程中都执行完countDown()方法
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("我处理完了");
    }

    class doPicThread extends Thread {

        private CountDownLatch l;
        private int id=0;

        public doPicThread(CountDownLatch l,int id) {
            this.l = l;
            this.id = id;
        }

        public void setL(CountDownLatch l) {
            this.l = l;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " 我拿到了："
                        + picInfo);
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                l.countDown();//注意这里countDown极力推荐写在finally中，因为如果由于某种异常这个方法没有执行那么主线程就要一直等待下去了
            }
        }
    }

    public static void main(String[] args) {
        new Pic("iiii").doPic(10);
    }


}
