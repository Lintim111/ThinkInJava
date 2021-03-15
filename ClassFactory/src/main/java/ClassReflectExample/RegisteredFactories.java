package ClassReflectExample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Part {
    public String toString() {
        return getClass().getSimpleName() + " , " + this.Id;
    }

    static List<Factory<? extends Part>> partFactories = new ArrayList<Factory<? extends Part>>();

    static {
        partFactories.add(new FuelFilter.Factory());
        partFactories.add(new AirFilter.Factory());

        partFactories.add(new FanBelt.Factory());
    }

    private static Random rand = new Random(47);

    public static Part createRandom() {
        int n = rand.nextInt(partFactories.size());
        return partFactories.get(n).create();
    }

    private Integer Id = rand.nextInt(100);

    public int getId() {
        return this.Id;
    }
}

class Filter extends Part{}

class FuelFilter extends Filter{
    public static class Factory implements ClassReflectExample.Factory<FuelFilter>{
        @Override
        public FuelFilter create(){
            return new FuelFilter();
        }
    }
}


class AirFilter extends Filter{
    public static class Factory implements ClassReflectExample.Factory<AirFilter>{
        @Override
        public AirFilter create(){
            return new AirFilter();
        }
    }
}

class Belt extends Part{}

class FanBelt extends Belt{
    public static class Factory implements ClassReflectExample.Factory<FanBelt>{
        @Override
        public FanBelt create() {
            return new FanBelt();
        }
    }
}

public class RegisteredFactories {
    public static void main(String[] args) {

        TypeCounter counter = new TypeCounter(Part.class);


        for (int i = 0; i < 10; i++) {
            Part p = Part.createRandom();
            System.out.println(p);
            counter.count(p);
        }

        System.out.println(counter);

        Part p = new AirFilter();
        System.out.println("AirFilter p is instanceof Part = " + (p instanceof Part));
        System.out.println("AirFilter p is instanceof Filter = " + (p instanceof Filter));
        System.out.println("AirFilter p is instanceof Filter = " + (p instanceof AirFilter));
        System.out.println("AirFilter p isinstance Filter.class = " + (Filter.class.isInstance(p)));
        System.out.println("AirFilter.class.isAssignableFrom(Filter.class)) = " + (AirFilter.class.isAssignableFrom(Filter.class)));
        System.out.println("Part.class.isAssignableFrom(AirFilter.class)) = " + (Part.class.isAssignableFrom(AirFilter.class)));//向上隐式转换


        try {
            Part p2 = AirFilter.class.newInstance();
            System.out.println("AirFilter.class.newInstance(): " + p2 + " , Id = " + p2.getId());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
