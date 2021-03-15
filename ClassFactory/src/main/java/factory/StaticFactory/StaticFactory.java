package factory.StaticFactory;

import factory.beans.DefaultItem;
import factory.beans.IItem;
import factory.beans.NewItem;

public class StaticFactory {

    public static IItem getItem(String itemType) {
        if (itemType.equals("new"))
            return new NewItem();
        return new DefaultItem();
    }
}
