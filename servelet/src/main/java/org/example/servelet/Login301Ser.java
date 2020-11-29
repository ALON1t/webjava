package org.example.servelet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//注解
//注解的使用：小括号包裹多个属性，属性名=属性值。，多个之间逗号间隔
//         属性名为value时可以缺省
//Servlet定义服务：注意服务路径必须是/开头，否则tomcat启动报错
//@WebServlet(value={"",""}，name="")
//@WebServlet(value = "")
@WebServlet("/login301")
public class Login301Ser extends HttpServlet {
    //每次http请求映射到某个Servlet的资源路径，都会调用service生命周期的方法
    //如果请求方法没有重写，就会调用父类的doXXX()(对应的请求方法)，返回405
    //如果重写，会将请求数据包装为一个Request请求对象，这时候虽然还没有响应，但是也包装了一个Response响应对象
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求，响应编码，及响应数据类型（为响应体设置Content-Type数据类型）
        req.setCharacterEncoding("UTF-8");//设置请求编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        //解析请求数据（req对象）
        //request.getParameter方法获取请求数据：url和请求体，数据格式为k1=va1&k2=v2
        String u = req.getParameter("username");
        String p = req.getParameter("password");
        System.out.printf("------------------用户名（%s） 密码（%s）%n",u,p);

        if("abc".equals(u)&&"123".equals(p)) {
            resp.sendRedirect("home.html");
        }
//        else if("abc".equals(u)) {
//            req.ggetRe
//        }
        else{
            //返回响应数据
            PrintWriter pw = resp.getWriter();//response获取io输出流
            pw.println("登录失败");
            pw.println("<h3>账号,"+u+"或密码错误</h3>");
            pw.flush();//有缓冲io的操作，需要刷新缓冲区，才会真正发送数据
            pw.close();//io流操作，一定要记住关闭资源
        }
    }
}