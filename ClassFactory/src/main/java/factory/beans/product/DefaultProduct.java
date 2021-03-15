package factory.beans.product;

public class DefaultProduct implements IProduct {
    @Override
    public void echo() {
        System.out.println("DefaultProduct");
    }
}
