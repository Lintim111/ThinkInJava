package Thread;

public class MyThread extends Thread {
    public MyThread() {

    }
    public void run() {
        for(int i=0;i<10;i++) {
            System.out.println(Thread.currentThread()+":"+i);
        }
    }
    public static void main(String[] args) {
        MyThread mThread1=new MyThread();
        MyThread mThread2=new MyThread();
        MyThread myThread3=new MyThread();
        mThread1.start();
        mThread2.start();
        myThread3.start();
    }
}
