package VendingMachine.Utils;

public class Circle implements IShape {
    @Override
    public int size() {
        return 0;
    }

    @Override
    public String name() {
        return "Circle";
    }
    public static void main(String[] args){
        IShape shape=new Circle();
        System.out.println(shape.name());
    }
}
