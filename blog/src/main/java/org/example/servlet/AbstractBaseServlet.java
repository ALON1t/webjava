package org.example.servlet;

import org.example.exception.AppException;
import org.example.model.JSONResponse;
import org.example.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class AbstractBaseServlet extends HttpServlet { //抽象类

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //设置请求体的编码格式
        req.setCharacterEncoding("Utf-8");
        //设置响应体编码
        resp.setCharacterEncoding("UTF-8");
        //设置响应体的数据类型（浏览器要采取什么方式执行）
        resp.setContentType("application/json"); //设置响应头

        //TODO session会话管理：除登录和注册接口，其他都需要登陆后访问
        //req.getServletPath() 获取服务路径

        //约定好的统一数据格式
        JSONResponse json = new JSONResponse();
        try {
            //调用子类重写的方法
            Object data = process(req, resp);
            //子类的process方法执行完没有抛异常，表示业务执行成功
            json.setSuccess(true);
            json.setData(data);

        } catch (Exception e) {
            //统一异常处理
            //异常如何处理？JDBC异常，JSON处理的异常，自定义异常 来返回错误消息
            e.printStackTrace(); //打印异常，不要吃异常
            //json.setSuccess(false)不用设置，new的时候默认false
            String code = "UNKNOWN";
            String s = "服务器未知错误";
            if (e instanceof AppException) {
                code = ((AppException) e).getCode(); //先强转为自定义异常，再获取
                s = e.getMessage();
            }
            //设置到json  前后端统一的数据封装
            json.setCode(code);
            json.setMessage(s);
        }
        //try catch两部分代码都要返回给前端json字符串
        PrintWriter pw = resp.getWriter();
        pw.println(JSONUtil.serialize(json)); //序列化对象
        pw.flush();
        pw.close();
    }

    //进入get和post之后都调用process()
    //子类重写
    protected abstract Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception; //模板方法

}
