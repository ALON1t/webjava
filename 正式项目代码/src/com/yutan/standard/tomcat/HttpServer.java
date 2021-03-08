package com.yutan.standard.tomcat;

import java.io.*;
import java.util.*;

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

    private static void initServer() {
        scanContexts();
        parseContextConf();
//        loadServletClasses();
//        instantiateServletObjects();
//        initializeServletObjects();
    }

    private static void parseContextConf() {
        for (Context context : contextList) {
            // 进行 web.conf 文件的读取 + 解析

            // web.conf 放哪里，必须符合规范，否则就会读不到
            String filename = String.format("%s/%s/WEB-INF/web.conf", WEBAPPS_BASE, context.getName());

            String stage = "start"; // "servlets"/"mappings"

            // 进行文本文件内容的读取
            try (InputStream is = new FileInputStream(filename)) {
                Scanner scanner = new Scanner(is, "UTF-8");
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    if (line.isEmpty() || line.startsWith("#")) {
                        // 如果是空行、或者注释行，跳过
                        continue;
                    }

                    switch (stage) {
                        case "start":
                            if (line.equals("servlets:")) {
                                stage = "servlets";
                            }
                            break;
                        case "servlets":
                            if (line.equals("servlet-mappings:")) {
                                stage = "mappings";
                            } else {
                                // 进行 ServletName => ServletClassName 的解析
                                String[] parts = line.split("=");
                                String servletName = parts[0].trim();
                                String servletClassName = parts[1].trim();
                                context.servletNameToServletClassNameMap.put(servletName, servletClassName);
                            }
                            break;
                        case "mappings":
                            // 进行 URL => ServletName 的解析
                            String[] parts = line.split("=");
                            String url = parts[0].trim();
                            String servletName = parts[1].trim();
                            context.urlToServletNameMap.put(url, servletName);
                            break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 进行配置文件的解析工作（标准）

            System.out.println(context.servletNameToServletClassNameMap);
            System.out.println(context.urlToServletNameMap);
        }
    }

    private static final String WEBAPPS_BASE = "D:\\课程\\2021-3-4-Java31-40班-HTTP项目\\正式项目代码\\webapps";
    private static final List<Context> contextList = new ArrayList<>();
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
            Context context = new Context(contextName);

            contextList.add(context);
        }
    }
}
