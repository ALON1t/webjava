package org.example.servlet;

import org.example.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class AbstractBase extends HttpServlet { //抽象类

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            //设置请求，响应编码，及响应数据类型（为响应体设置Content-Type数据类型）
            req.setCharacterEncoding("UTF-8");//设置请求编码
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html");

            //Session会话管理：除登录和注册接口，其他都需要登陆后访问
            //通过req.getServletPath() 方法获取请求服务路径
            //req.getServletPath();
            //TODO

            //调用子类重写的方法
            process(req,resp);
        } catch (Exception e) {
            //异常如何处理？
            //JDBC 的异常（假设是SQLException），JSON 处理的异常,自定义异常返回错误信息
            e.printStackTrace();
            String s = "服务器未知的错误";
            if(e instanceof AppException) {//e等于自定义异常
                s = e.getMessage();
            }
            PrintWriter pw = resp.getWriter();
            pw.println(s);
            pw.flush();
            pw.close();
            // 预留 TODO

        }
    }

    //protected 子类可以用
    protected abstract void process(HttpServletRequest req, HttpServletResponse resp) throws Exception;


}
