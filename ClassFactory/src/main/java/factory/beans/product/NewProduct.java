package factory.beans.product;

public class NewProduct implements IProduct {
    @Override
    public void echo() {
        System.out.println("NewProduct");
    }
}
