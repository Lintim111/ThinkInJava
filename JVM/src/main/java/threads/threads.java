package threads;

public class threads {
    private int a = 0;
    private int count = 0;
    private static int staticA = 0;

    synchronized public void setA(int a) {
        System.out.println("setA in " + Thread.currentThread().getName());
        this.a = a;
        this.count++;
        //System.out.println();
    }

    synchronized public static void setStaticA(int a) {
        System.out.println("setStaticA " + a + " in " + Thread.currentThread().getName());
        staticA = a;

    }

}
