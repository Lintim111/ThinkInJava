package GC;

public class GCTest {

    private static final int _1MB = 1024 * 1024;

    /**
     * vm参数: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseG1GC
     */
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];

    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("GCTest main");
        for(int i=0;i<10;i++){
            testAllocation();
            Thread.sleep(2000);
        }
    }
}
