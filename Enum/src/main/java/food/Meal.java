package food;

public class Meal {
    public static void main(String[] args){
        for(int i=0;i<5;i++){
            for(Course course: Course.values()){
                Food food=course.randSelection();
                System.out.println(food);
            }
            System.out.println("-----");
        }
    }
}
