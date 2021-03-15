package GC.cleaner;

//import java.lang.ref.Cleaner; // jdk 1.9

public class CleaningExample implements AutoCloseable {
    // A cleaner, preferably one shared within a library
    //private static final Cleaner cleaner = <cleaner>;
    static class State implements Runnable {
        State() {
            // initialize State needed for cleaning action
        }
        public void run() {
            // cleanup action accessing State, executed at most once
        }
    }
    private final State state;
    //private final Cleaner.Cleanable cleanable;
    public CleaningExample() {
        this.state = new State();
        //this.cleanable = cleaner.register(this, state);
    }
    public void close() {
        //cleanable.clean();
    }
}