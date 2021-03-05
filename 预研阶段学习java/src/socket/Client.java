package socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8080);
        //Socket socket = new Socket("42.193.120.201", 8080);
        // socket 已经建立好连接了

        InputStream inputStream = socket.getInputStream(); //拿到输入流
        Scanner scanner = new Scanner(inputStream, "UTF-8");

        OutputStream outputStream = socket.getOutputStream(); //拿到输出流
        Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
        PrintWriter printWriter = new PrintWriter(writer);

        printWriter.printf("你好，我是中国人\r\n"); // 向服务器发送 消息  写
        printWriter.flush();    // 只要进行了刷新(flush)操作，才能把数据真正写入

        String message = scanner.nextLine();        // 从服务器读取 消息  读
        System.out.println(message);

        socket.close();
    }
}
