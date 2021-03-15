package serialize;

import java.io.Serializable;
import java.util.Random;

public class User implements Serializable {
    private static Random random = new Random();
    //序列化ID
    private static final long serialVersionUID = 1L;
    private int age;
    private String name;
    private UserData userData = new UserData(random.nextInt(100));

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    @Override
    public String toString() {
        return "serialVersionUID: " + serialVersionUID + ", age: " + age + ", name" + name + ", userData" + userData;
    }

    public class UserData implements Serializable {

        private int data;
        public UserData() {

        }

        public UserData(int i) {
            data = random.nextInt(i);
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "(" + this.data + ")";
        }
    }
}
