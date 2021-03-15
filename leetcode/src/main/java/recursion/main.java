package recursion;

public class main {


    public static void main(String[] args){

        ITestRunner runner = new ReverseString();
        //runner.run();

        runner=new ReverseLinkedList();
        runner.run();
    }
}
