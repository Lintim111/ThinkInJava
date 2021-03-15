package factory.beans;

public class NewItem extends BaseItem {
    private static final String name = "item";
    private static final String newName = "defaultItem";

    private int item = 0;

    private int option_a = 0;
    private int option_b = 0;
    private int option_c = 0;
    private int option_d = 0;

    public NewItem() {
        System.out.println(newName);
    }

    public NewItem(int item) {
        this.item = item;
    }

    public int getItem() {
        return item;
    }

    @Override
    protected void doProduct() {
        System.out.println(this.name + getItem());
    }
}
