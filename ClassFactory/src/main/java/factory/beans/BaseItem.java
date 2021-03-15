package factory.beans;

public abstract class BaseItem implements IItem {
    private static final String name = "base";

    public BaseItem() {
        System.out.println(name);
    }

    @Override
    public void product() {
        System.out.println(name);
        doProduct();

    }

    protected void doProduct() {

    }
}
