package factory.StaticFactory.abstractFactory;

import factory.beans.DefaultItem;
import factory.beans.IItem;
import factory.beans.NewItem;
import factory.beans.product.DefaultProduct;
import factory.beans.product.IProduct;
import factory.beans.product.NewProduct;

public class FactoryB implements IAbstractFactory {
    @Override
    public IItem getItme() {
        return new DefaultItem();
    }

    @Override
    public IProduct getProduct() {
        return new DefaultProduct();
    }
}
