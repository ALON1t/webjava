package org.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

//注解 后面一定要加上属性，属性名为value时可以不写
//小括号包裹多个属性，属性名=属性值，多个之间逗号间隔
//例如：@WebServlet(value={"",""},name="")
//Servlet定义服务：注意服务路径必须时 / 开头，否则tomcat启动会报错 -- 对应uri
@WebServlet("/sen")
public class SensitiveServlet extends HttpServlet {
    //重写方法  提供服务
    //注意get和post的区别

    /**
     * 每次http请求映射到某个Servlet的资源路径，都会调用service生命周期方法
     * 如果请求方法没有重写，就调用父类的doXX方法（对应的请求方法），返回405
     * 如果重写，会将请求数据包装为Request对象，这时候虽然还没有响应，但是也包装了一个Response响应对象
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置响应请求，响应编码及响应数据类型（为响应体设置Content-Type数据类型）
        req.setCharacterEncoding("UTF-8"); //设置请求体编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        //返回响应数据
        PrintWriter pw = resp.getWriter(); //response获取io输出流
        HttpSession session = req.getSession(false);
        //每一个敏感资源，都获取session，通过是否为空验证用户是否登录,但是代码耦合性太高
        if (session == null) { //可以使用filter过滤器，统一处理敏感资源验证
            System.out.println("session为空");
            resp.setStatus(401); //没有登录，访问敏感资源，返回401，表示Unauthorized
            pw.println("敏感资源，需要登录后访问");
        } else {
            String username = (String) session.getAttribute("username");
            System.out.println("session存在，用户名为：" + username);
        }
        pw.flush(); //有缓冲的io操作，需要刷新缓冲区，才会真正的发送数据  缓冲刷新
        pw.close(); //io流操作完，一定要记得关闭资源

    }
}
