package methodinvoke.sub;

public class superclass {
    private void method1(){
        System.out.println("super method1");
    }

    void method2(){
        System.out.println("super method2");
    }
    protected void method3(){
        System.out.println("super method3");
    }
    public void method4(){
        System.out.println("super method4");
    }

    void defaultInvoke(){
        method1();
        method2();
        method3();
        method4();
    }

    protected void protectedInvoke(){
        method1();
        method2();
        method3();
        method4();
    }
    public void publicInvoke(){
        method1();
        method2();
        method3();
        method4();
    }
}
