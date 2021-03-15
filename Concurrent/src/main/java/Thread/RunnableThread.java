package Thread;

public class RunnableThread {
    public void main(String[] args){
        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("11");
            }
        });
        t.start();
    }
}
