package factory.StaticFactory.abstractFactory;

import factory.beans.IItem;
import factory.beans.product.IProduct;

public interface IAbstractFactory {
    IItem getItme();
    IProduct getProduct();
}
