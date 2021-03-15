package food;

public interface Food {
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
