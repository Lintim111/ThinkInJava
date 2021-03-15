package ClassLoad.Instance;


public class Parent implements Cloneable {
    private static int statica=1;
    private int a=1;
    private String s;
    public int common=100;

    static {
        System.out.println("static{} in Parent");
        System.out.println( "Parent statica: "+ statica);
        statica =12;
    }

    public Parent(){
        this(-1);
        System.out.println("init by parent()");
    }

    public Parent(int a){
        System.out.println(this);
        this.a=a;
        System.out.println("init by parent(int a)");
    }

    public Parent(String s){
        System.out.println("init by parent(String s)");
        // this(2); // this(), this(xx) must in first line;
        this.s = s;
    }

    @Override
    public String toString() {
        return super.toString()+", parent: { a="+this.a+", s="+this.s+",common= "+this.common+" }";
    }
}
