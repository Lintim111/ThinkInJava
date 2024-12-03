package Synchronizer.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author lintianqiao
 * @date 2024/12/03/17:13
 */
public class CyclicBarrierMain {
  public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
    CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
    //获取参与方的总数
    System.out.println("参与方的总数为：" + cyclicBarrier.getParties());
    //获取此时等待的线程数
    System.out.println("此时等待的线程数为：" + cyclicBarrier.getNumberWaiting());

    for (int i = 0; i < cyclicBarrier.getParties(); i++) {
      CyclicBarrierRunnable runnable = new CyclicBarrierRunnable(cyclicBarrier, i);
      new Thread(runnable).start();
    }

  }

  static class CyclicBarrierRunnable implements Runnable {
    private CyclicBarrier cyclicBarrier;
    private int number;

    public CyclicBarrierRunnable(CyclicBarrier cyclicBarrier, int number) {
      this.cyclicBarrier = cyclicBarrier;
      this.number = number;
    }

    @Override
    public void run() {
      System.out.println("玩家" + number + "号正在加载游戏...");
      try {
        TimeUnit.SECONDS.sleep(2);
        cyclicBarrier.await();
      } catch (Exception e) {
        System.out.println("线程执行出现问题");
      }
      System.out.println("玩家" + number + "号加载完成。");
    }
  }
}
