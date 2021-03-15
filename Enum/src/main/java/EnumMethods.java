public enum EnumMethods {

    CPU;
    //CPU(ShowCPU.class),
    //MEMORY(ShowMemory.class);
    public interface Executor{
        public String run();
    }

    public class ShowMemory implements Executor{

        @Override
        public String run() {
            return "Show Memory";
        }
    }
    private class ShowCPU implements Executor{

        @Override
        public String run() {
            return "Show CPU";
        }
    }

    private Executor executor;

    /*private EnumMethods(Class<Executor> methodClass){
        executor=methodClass.newInstance();
    }*/

}
