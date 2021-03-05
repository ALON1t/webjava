package v1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HTTPServer {

    //多线程
    public static void main2(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket socket = serverSocket.accept();

            Runnable task = new RequestResponseTask(socket);
            new Thread(task).start();
        }
    }
    //固定线程池
    public static void main(String[] args) throws IOException {
        // 采用固定大小线程池
        // 不是最优的方式，但简单
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket socket = serverSocket.accept();

            Runnable task = new RequestResponseTask(socket);
            threadPool.execute(task);
        }
    }

    public static void main1(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket socket = serverSocket.accept();

            System.out.println("一条tcp已经建立成功");
            //暂时不读取/解析请求

            //直接写回响应
            OutputStream outputStream = socket.getOutputStream();
            Writer writer = new OutputStreamWriter(outputStream,"UTF-8");
            PrintWriter printWriter = new PrintWriter(writer);

            Thread.sleep(10_000);
            //单线程处理  若浏览器同时发起了三个请求 第一个需要10s 第二个需要20s 第三个需要30s
            //解决：  使用多线程  线程池

            //写响应
            //写响应行
            printWriter.printf("HTTP/1.0 200 OK\r\n");
            //写响应头
            printWriter.printf("Content-Type : text/html;charset=utf-8\r\n");
            //printWriter.printf("Content-Type : text/plain;charset=utf-8\r\n");
            //写入空行 代表响应头结束
            printWriter.printf("\r\n");
            //写响应体 html内容
            printWriter.printf("<h1>正常工作了</h1>");
            //刷新 把数据写入 TCP 发送缓冲区
            printWriter.flush();

            socket.close();
            System.out.println("释放");
        }
    }

}
