package com.yutan.standard.tomcat;

import com.peixinchen.standard.Servlet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    public static void main(String[] args) throws IOException {
        // 1. 找到所有的 Servlet 对象，进行初始化
        initServer();
//
//        ExecutorService threadPool = Executors.newFixedThreadPool(10);
//        ServerSocket serverSocket = new ServerSocket(8080);
//
//        // 2. 每次循环，处理一个请求
//        while (true) {
//            Socket socket = serverSocket.accept();
//            Runnable task = new RequestResponseTask(socket);
//            threadPool.execute(task);
//        }
//
//        // 3. 找到所有的 Servlet 对象，进行销毁
//        for (Servlet servlet : servlets) {
//            servlet.destroy();
//        }
    }

    private static void initServer() throws IOException {
        scanContexts();
        parseContextConf();
//        loadServletClasses();
//        instantiateServletObjects();
//        initializeServletObjects();
    }

    private static void parseContextConf() throws IOException {
        for (Context context : contextList) {
            context.readConfigFile();
        }
    }

    public static final String WEBAPPS_BASE = "D:\\课程\\2021-3-4-Java31-40班-HTTP项目\\正式项目代码\\webapps";
    private static final List<Context> contextList = new ArrayList<>();
    private static final ConfigReader configReader = new ConfigReader();
    private static void scanContexts() {
        File webappsRoot = new File(WEBAPPS_BASE);
        File[] files = webappsRoot.listFiles();
        if (files == null) {
            throw new RuntimeException();
        }

        for (File file : files) {
            if (!file.isDirectory()) {
                // 不是目录，就不是 web 应用
                continue;
            }

            String contextName = file.getName();
            System.out.println(contextName);
            Context context = new Context(configReader, contextName);

            contextList.add(context);
        }
    }
}
