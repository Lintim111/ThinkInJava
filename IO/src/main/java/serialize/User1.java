package serialize;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class User1 implements Externalizable {
    private int age;
    private String name;
    //getter、setter
    //toString方法

    public User1() {}

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
    }
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    }
}
