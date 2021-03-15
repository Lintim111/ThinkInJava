package collaborate.blockingqueue;

import java.util.Random;
import java.util.concurrent.*;

class Toast{
    public enum Status {DRY, BUTTERED, JAMMED}
    private Status status = Status.DRY;
    private final int id;
    public Toast(int idn){
        id = idn;
    }
    public void butter(){
        status=Status.BUTTERED;
    }
    public void jam(){
        status=Status.JAMMED;
    }
    public Status getStatus(){
        return status;
    }
    public int getId(){
        return id;
    }
    public String toString(){
        return "Toast "+ id+": "+status;
    }
}

class ToastQueue extends LinkedBlockingQueue<Toast> {}

class Toaster implements Runnable{
    private ToastQueue toastQueue;
    private int count =0;
    private Random random = new Random(47);
    public Toaster(ToastQueue queue){toastQueue=queue;}

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(100+random.nextInt(500));
                Toast t = new Toast(count++);
                System.out.println("New "+t);
                toastQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Toaster interrupted");
        }
        System.out.println("Toaster off");
    }
}

class Butterer implements Runnable{

    private ToastQueue dryQueue, butterQueue;
    public Butterer(ToastQueue dryQ,ToastQueue butterQ){
        dryQueue=dryQ;
        butterQueue=butterQ;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                Toast t=dryQueue.take();
                t.butter();
                System.out.println("Buttering "+t);
                butterQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Butterer interrupted");
        }
        System.out.println("Butterer off");
    }
}

class Jammer implements Runnable{

    private ToastQueue butteredQueue, finishedQueue;
    public Jammer(ToastQueue butteredQ, ToastQueue finishedQ){
        butteredQueue=butteredQ;
        finishedQueue =  finishedQ;
    }
    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                System.out.println("try jammer");
                Toast t = butteredQueue.take();
                t.jam();
                System.out.println("Jaming "+t);
                finishedQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Jammer off");
        }
        System.out.println("Jammer off");

    }
}

class Eater implements Runnable{

    private ToastQueue finishedQueue;
    private int counter =0;
    public Eater(ToastQueue finishedQ){
        finishedQueue=finishedQ;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                Toast t = finishedQueue.take();
                if(t.getId()!=counter++||t.getStatus()!= Toast.Status.JAMMED){
                    System.out.println(">>>> Error: "+t);
                    System.exit(1);
                }
                else{
                    System.out.println("Chomp! "+t);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Eater interrupted");
        }

        System.out.println("Eater off");
    }
}

public class ToastOMatic {
    public static void main(String[] args) throws InterruptedException {

        ToastQueue dryQueue = new ToastQueue(),
                butteredQueue = new ToastQueue(),
                finishedQueue = new ToastQueue();
        ExecutorService ex = Executors.newCachedThreadPool();
        ex.execute(new Toaster(dryQueue));
        ex.execute(new Butterer(dryQueue,butteredQueue));
        ex.execute(new Jammer(butteredQueue,finishedQueue));
        ex.execute(new Eater(finishedQueue));
        TimeUnit.SECONDS.sleep(5);
        ex.shutdownNow();
    }
}
