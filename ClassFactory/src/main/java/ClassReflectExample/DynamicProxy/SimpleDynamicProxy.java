package ClassReflectExample.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SimpleDynamicProxy {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("hello");
    }

    public static void main(String[] args) {
        RealObject real = new RealObject();
        consumer(real);
        Interface proxy = (Interface) Proxy.newProxyInstance(
                Interface.class.getClassLoader(),
                new Class[]{Interface.class},
                new DynamicHandler(real)
        );
        consumer(proxy);

        System.out.println("----------------");

        RealObject2 real2 = new RealObject2();
        consumer(real2);
        Interface proxy2 = (Interface) Proxy.newProxyInstance(
                Interface.class.getClassLoader(),
                new Class[]{Interface.class, Interface2.class},
                new DynamicHandler(real2)
        );
        consumer(proxy2);
    }
}

interface Interface2{
    void doOneThing();
    void doOtherThing(String arg);
}
class RealObject2 implements Interface, Interface2 {

    @Override
    public void doSomething() {
        System.out.println("doSomething");
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("somethingElse " + arg);
    }

    @Override
    public void doOneThing() {
        System.out.println("doOneThing");
    }

    @Override
    public void doOtherThing(String arg) {
        System.out.println("doOtherThing " + arg);
    }
}

class DynamicHandler implements InvocationHandler {
    private Object proxied;

    public DynamicHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("**** proxy " + proxy.getClass() + ", method: " + method + ", args: " + args);
        if (args != null) {
            for (Object arg : args) {
                System.out.println("  " + arg);
            }
        }
        return method.invoke(proxied, args);
    }
}