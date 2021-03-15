package jit;

public class Test {
    public final static int NUM = 150000;

    public static int doubleValue(int i) {
        for (int j = 0; j < 1000000; j++) ;
        return i * 2;
    }

    public static long calcSum() {
        long sum = 0L;
        for (int i = 0; i <= 100; i++) {
            sum += doubleValue(i);
        }
        return sum;
    }

    public static void main(String[] args) {
        for (int i = 0; i < NUM; i++) {
            calcSum();
        }
    }
}
