package ClassLoad.Instance;

public class child extends Parent {

    private int b = 1;

    private static String s = "1";

    static {

        System.out.println("static{} in child");
        System.out.println("child s:" + s);
        s = "hello";
    }

    child() {

    }

    child(int a) {
        System.out.println(this);
        this.b = a;
        this.common = a * 2;
        System.out.println(this);
        System.out.println("child(int a)");
        this.b = a;
        // super(a); //super() super(xx) must in first line
    }

    child(String s) {
        System.out.println("child(String s)");
    }

    @Override
    public String toString() {
        return super.toString() + ", child: { b=" + this.b + " , common=" + this.common + "}";
    }

    public static void main(String[] args) throws Exception {
        Parent a = new child();
        System.out.println("---1");
        Parent p = new child(12);
        System.out.println("---2");
        child c = new child("sd");

        System.out.println("---3");
        try {
            //Parent p1 = new Parent(12);
            //Parent clone1 = (Parent) p1.clone();
            System.out.println("Clone ");
            child clone2 = (child) c.clone();
            System.out.print("clone: " + clone2);
        } catch (CloneNotSupportedException e) {
            System.out.println("CloneNotSupportedException");
        }

        System.out.println(p);//override, invoked by real instance(not by class type )
        System.out.println(c);
    }
}
