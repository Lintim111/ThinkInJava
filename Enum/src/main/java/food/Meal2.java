package food;

public enum Meal2 {


    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class);

    private Food[] foods;

    public interface Food{
        enum Appetizer implements Food{
            SALAD,SOUP,SPRING_ROLLS;
        }

        enum MainCourse implements Food{
            LASAGNE,BURRITD
        }

        enum Dessert implements Food{
            ICE_CRAME, GELATO,FRUIT
        }

        enum Coffee implements Food{
            BLACK_COFFEE,DECAF_COFFEE,ESPRESSO,LATTE,TEA
        }
    }

    private Meal2(Class<? extends Food> kind){
        foods=kind.getEnumConstants();
    }

    public Food randomSelection(){
        return Enums.random(foods);
    }

    public class Fields{
        public String name="hello";
        private int age = 12;
    }

    public static void main(String args[]){
        for(int i=0;i<5;i++){
            for (Meal2 meal2: Meal2.values()) {
                System.out.println(meal2.randomSelection());
            }
            System.out.println("-----");
        }

        System.out.println(Meal2.class.getEnumConstants());
        System.out.println(Fields.class.getEnumConstants());
    }

}
