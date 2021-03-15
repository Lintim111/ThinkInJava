package Atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class NumberRange {
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);

    public synchronized void setLower(int i) {
        if (i > upper.get()) {
            throw new IllegalArgumentException("Illegal set lower > upper");
        }
        lower.set(i);
    }

    // lower.get后upper.set前的变更没有原子性, 要加锁(当前class对象)对当前类做互斥(串行)处理
    public synchronized void setUpper(int i) {
        if (i < lower.get()) {
            throw new IllegalArgumentException("Illegal set upper, upper < lower");
        }
        upper.set(i);
    }

    // 同时操作lower和upper2个资源，虽然单一资源由AtomicInteger保证了原子性，但
    public synchronized String toString() {
        return "lower: " + lower.get() + ", upper: " + upper.get();
    }

    public static void main(String[] args) {
        NumberRange numberRange = new NumberRange();
        numberRange.setUpper(10);
        numberRange.setLower(1);
        System.out.println(numberRange);
    }
}
