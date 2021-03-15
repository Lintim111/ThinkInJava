public class FooTest1 {
    public static void test1(){

        boolean 吃饭没=true;
        if(吃饭没)
            System.out.println("yes");
        if(true==吃饭没){
            System.out.println("really yes");
        }
    }
    static boolean booleanvalue;
    public static void test2(){

        booleanvalue=true;
        if(booleanvalue)
            System.out.println("yes");
        if(true==booleanvalue){
            System.out.println("really yes");
        }
    }
    public static void main(String[] args){
        test1();
        test2();
    }
}
