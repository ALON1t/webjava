package v3;

import java.io.*;

public class SerializableDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String filename = "C:\\Users\\LENOVO\\预研阶段学习java\\sessions\\users.obj";
//        User u1 = new User(1, "陈沛鑫", "男");
//        User u2 = new User(2, "林黛玉", "女");
//持久化  序列化

//        try (OutputStream outputStream = new FileOutputStream(filename)) {
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
//
//            objectOutputStream.writeObject(u1);
//            objectOutputStream.writeObject(u2);
//
//            objectOutputStream.flush();
//        }

//反序列化
        try (InputStream inputStream = new FileInputStream(filename)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            User u1 = (User)objectInputStream.readObject();
            User u2 = (User)objectInputStream.readObject();

            System.out.println(u1);
            System.out.println(u2);
        }
    }
}
