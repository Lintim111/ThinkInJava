package serialize;

import java.io.*;

public class test {
    private static String filePath;

    static {
        filePath = "serialize.test.txt";
    }

    public static void main(String[] args) throws Exception, IOException {
        SerializeUser();
        DeSerializeUser();
    }

    //序列化方法
    private static void SerializeUser() throws FileNotFoundException, IOException {
        User user = new User();
        user.setName("Java的架构师技术栈");
        user.setAge(24);
        //序列化对象到文件中
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
        oos.writeObject(user);
        oos.close();
        System.out.println("序列化对象成功");
    }

    //反序列化方法
    private static void DeSerializeUser() throws FileNotFoundException, IOException, ClassNotFoundException {
        File file = new File(filePath);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        User newUser = (User) ois.readObject();
        System.out.println("反序列化对象成功" + newUser.toString());

    }
}
