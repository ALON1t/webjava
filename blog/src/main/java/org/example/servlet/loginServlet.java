package org.example.servlet;

import org.example.dao.LoginDAO;
import org.example.exception.AppException;
import org.example.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 执行流程： 客户端发起一个url请求(请求一个网页)
 */
@WebServlet("/login")
public class loginServlet extends AbstractBaseServlet{
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        /**
         * Ajax请求：携带前端的内容作为请求数据
         * 服务端解析请求数据：
         *    （1）queryString:url中请求数据
         *    （2）表单数据类型 x-www-form-urlencoded  （1）（2）都是k1=v1&k2=v2格式
         *        req.getParameter("键")  获取数据
         *    （3）请求数据Content-Type:application/json
         *        req.getInputStream获取请求体，数据作为输入流获取
         *        再通过json框架，反序列化为一个Java对象，请求数据和对象属性要一致，否则会报错
         */

        /**
         * 服务端返回响应数据：都返回application/json数据类型
         */

        /**
         * Servlet代码逻辑：
         *    （1）获取请求数据
         *    （2）如果请求数据类型为application/json，反序列化为Java对象
         *    （3）执行业务操作，包括数据库CRUD
         *    （4）返回响应数据：返回一个Java对象，使用该对象序列化为json字符串
         */

        //获取请求数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = LoginDAO.query(username);
        if (user == null) {
            throw new AppException("LOG002","用户不存在");
        }
        if(!user.getPassword().equals(password)) {
            throw new AppException("LOG003","用户名或密码错误");
        }
        //登录成功，创建session
        HttpSession session = req.getSession();
        session.setAttribute("user",user);
        return null;

        ////模拟数据库执行操作
//        if ("abc".equals(username) && "123".equals(password)) { //模拟用户名密码校验
//            //转发和重定向的区别：
//            //转发：forward() 由服务器端进行的页面跳转，地址不变
//            //重定向：sendRedirect() 由浏览器端进行的页面跳转  地址改变
//            //resp.sendRedirect("jsp/articleList.jsp");
//            return null;
//        } else {
//            throw new AppException("LOG001","用户名或密码错误");
//        }
    }


}
