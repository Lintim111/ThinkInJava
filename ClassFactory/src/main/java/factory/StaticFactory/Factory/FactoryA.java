package factory.StaticFactory.Factory;

import factory.StaticFactory.IFactory;
import factory.beans.IItem;
import factory.beans.NewItem;

public class FactoryA implements IFactory {
    @Override
    public IItem getItem() {
        System.out.println("FactoryA");
        return new NewItem();
    }
}
