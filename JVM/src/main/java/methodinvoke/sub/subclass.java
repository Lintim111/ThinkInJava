package methodinvoke.sub;

public class subclass extends superclass {
    private void method1(){
        System.out.println("subclass method1");
    }

    void method2(){
        System.out.println("subclass method2");
    }
    protected void method3(){
        System.out.println("subclass method3");
    }
    public void method4(){
        System.out.println("subclass method4");
    }

    public static void main(String[] args){
        subclass sub1 = new subclass();
        System.out.println("defaultInvoke");
        sub1.defaultInvoke();
        System.out.println("protectedInvoke");
        sub1.protectedInvoke();
        System.out.println("publicInvoke");
        sub1.publicInvoke();
    }
}