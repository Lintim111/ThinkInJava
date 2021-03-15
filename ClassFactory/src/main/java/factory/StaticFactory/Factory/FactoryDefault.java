package factory.StaticFactory.Factory;

import factory.StaticFactory.IFactory;
import factory.beans.DefaultItem;
import factory.beans.IItem;

public class FactoryDefault implements IFactory {
    @Override
    public IItem getItem() {
        System.out.println("FactoryDefault");
        return new DefaultItem();
    }
}
