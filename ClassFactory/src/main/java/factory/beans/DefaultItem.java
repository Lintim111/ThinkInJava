package factory.beans;

public class DefaultItem extends BaseItem {
    private static final String name = "defaultItem";
    private static final String defaultName = "defaultItem";

    private int item = 0;

    private int option_a = 0;
    private int option_b = 0;
    private int option_c = 0;
    private int option_d = 0;

    public DefaultItem() {
        System.out.print(name);
    }

    public DefaultItem(int item) {
        this.item = item;
    }

    public int getItem() {
        return item;
    }


    @Override
    protected void doProduct() {
        System.out.println("defaultItem: " + getItem());
    }
}
