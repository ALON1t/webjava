package com.yutan.standard;

import java.io.IOException;

public interface Servlet {
    //核心
    void init() throws ServletException; //初始化

    //收到请求--》根据请求找到对应的Servlet对象   servlet对象一生被调用一次
    //servlet.service(req,resp);  servlet对象一生被调用多次
    // 发送响应   servlet对象一生被调用一次
    void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException;

    void destroy(); //销毁
}
