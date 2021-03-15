package factory.main;

import factory.StaticFactory.Factory.FactoryA;
import factory.StaticFactory.Factory.FactoryDefault;
import factory.StaticFactory.IFactory;
import factory.StaticFactory.StaticFactory;
import factory.StaticFactory.abstractFactory.IAbstractFactory;
import factory.beans.IItem;

public class Main {
    public static void main(String[] args) {
        simpleFactoryTest();
        factoryFactoryTest();
        abstractFactoryTest();
    }

    public static void simpleFactoryTest() {
        IItem item = StaticFactory.getItem("new");
        item.product();
    }

    public static void factoryFactoryTest() {
        System.out.println("-----factoryFactoryTest-----");
        IFactory factory;
        IItem item;
        factory = new FactoryA();
        item = factory.getItem();
        item.product();

        factory = new FactoryDefault();
        item = factory.getItem();
        item.product();
    }

    public static void abstractFactoryTest(){
        // 生产一整套有产品的（至少要生产两个产品)，这些产品相互是有关系或有依赖的
        System.out.println("-----abstractFactoryTest-----");
        IAbstractFactory factory = new factory.StaticFactory.abstractFactory.FactoryA();
        factory.getItme().product();
        factory.getProduct().echo();
        factory = new factory.StaticFactory.abstractFactory.FactoryB();
        factory.getItme().product();
        factory.getProduct().echo();
    }
}
