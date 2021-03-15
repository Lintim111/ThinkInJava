package factory.StaticFactory.abstractFactory;

import factory.beans.IItem;
import factory.beans.NewItem;
import factory.beans.product.IProduct;
import factory.beans.product.NewProduct;

public class FactoryA implements IAbstractFactory {
    @Override
    public IItem getItme() {
        return new NewItem();
    }

    @Override
    public IProduct getProduct() {
        return new NewProduct();
    }
}
