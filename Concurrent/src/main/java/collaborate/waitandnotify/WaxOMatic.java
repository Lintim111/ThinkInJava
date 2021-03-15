package collaborate.waitandnotify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Car{
    private boolean waxon=false;
    public synchronized void waxed(){
        waxon=true;
        notifyAll();
        System.out.println("waxed notify all");
    }
    public synchronized void buffed(){
        waxon=false;
        notifyAll();
    }
    public synchronized void waitForWaxing() throws InterruptedException {
        while (!waxon){
            System.out.println("start waitForWaxing");
            wait();//收到notify 线程从这里开始执行
            System.out.println("---- after waitForWaxing ----");
        }
    }
    public synchronized void waitForBuffing() throws InterruptedException {
        while (waxon){
            System.out.println("start waitForBuffing");
            wait();
            System.out.println("after waitForBuffing");
        }
    }
}

class WaxOn implements Runnable{
    private Car car;
    public WaxOn(Car c){
        this.car=c;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){

                System.out.println("**** in WaxOn *****");
                System.out.print("Wax On! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                System.out.println("waxed");
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax On task");
    }
}

class WaxOff implements Runnable{
    private Car car;
    public WaxOff(Car c){
        this.car=c;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                System.out.println("**** in WaxOff *****");
                car.waitForWaxing();
                System.out.print("Wax Off! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax Off task");
    }
}

public class WaxOMatic {

    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
