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

/**
 * Servlet：
 *    servlet是否能返回一个网页？
 *       答：可以  可以使用resp.setContentType() 设置
 *             例如：resp.setContentType("text/html")  html格式  返回网页
 *                 resp.setContentType("application/octet-stream")  二进制流（常见的文件下载）
 *
 *       jsp:特殊的Servlet
 *           类似 html 语法，本质上还是servlet，使用输出流write() 输出网页内容
 *           名称：网页模板，其他类似如freemarker,velocity,theamleaf等（框架提供占位符，替换变量）
 *           作用：动态渲染  可以返回动态的网页内容
 *
 *   请求是否能请求任意内容？
 *      答：可以，通过request获取任意数据类型/类型
 *
 *    如何定位服务资源？请求url -->映射后端资源
 *    servlet如何接受http请求数据？
 *      1.queryString  通过request.getParameter("键")
 *      2.请求Content-Type:表单格式（x-www-form-urlencoded）  通过request.getParameter("键")
 *      3.无论请求数据类型是什么，请求体的数据都可以通过request.getInputStream()来做
 *         只要是自己解析
 *      4.特殊的：因为请求数据类型为
 *             表单格式 （使用简单方式：getParameter()）
 *             application/json  （1.使用第三个方式：getInputStream() 2.自己解析：使用第三方框架解析）
 *             form-data（文件格式），
 *           也可以通过第三点来获取，但是自己解析比较复杂  （使用简单方式：getParameter()）
 *
 *        （接收multipart/form-data请求数据类型
 *        多个键值对的方式，任意键值对都可以是数据，也可以是文件
 *        所以form-data支持多文件上传 + 多数据传输）
 */

//抽象类父类 模板方法
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
