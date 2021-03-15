package Atomic;

public class BigInteger {
    // BigInteger 使用
    private final int[] nums;

    public BigInteger(int[] nums){
        this.nums=nums;
    }

    public static int getsize(BigInteger bigInteger){
        return bigInteger.nums.length;
    }

    public static void main(String[] args){
        int[] a=new int[]{10,20,30};
        byte[] b = new byte[]{2,8,16,32};
        java.math.BigInteger bigInteger2 = new java.math.BigInteger(b);
        bigInteger2.toString();
    }
}
