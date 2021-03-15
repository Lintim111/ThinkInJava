package finallyTest;

public class FinallyTest {

    private int test1(){
        int a=0;
        try{
            a=1;

        }finally {
            a=2;
        }
        System.out.println(a);
        return a;
    }
    private static int test2(){
        int a=0;
        try{
            a=1;
            return a;

        }finally {
            a=2;
        }
    }

    static class obj{
        int a=0;
        public void setA(int a){
            this.a=a;
        }
        public int getA(){
            return a;
        }
    }

    private static obj test3(){
        obj objA= new obj();
        try{
            objA.setA(1);

        }finally {
            objA.setA(2);
        }
        return objA;
    }

    private static obj test4(){
        obj objA= new obj();
        try{
            objA.setA(1);
            return objA;

        }finally {
            objA.setA(2);
        }
    }

    private static int a=0;
    private static int test5(){
        a=0;
        try{
            a=1;

        }finally {
            a=2;
        }
        return a;
    }
    private static int test6(){
        a=0;
        try{
            a=1;
            return a;

        }finally {
            a=2;
        }
    }

    public static void main(String[] args){
        FinallyTest finallyTest=new FinallyTest();
        System.out.println(finallyTest.test1());
        System.out.println(test2());
        System.out.println(test3().getA());
        System.out.println(test4().getA());

        System.out.println(test5());
        System.out.println(test6());
    }
}
