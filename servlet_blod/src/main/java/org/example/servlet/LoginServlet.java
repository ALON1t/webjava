package org.example.servlet;

import org.example.exception.AppException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends AbstractBase{
    @Override
    protected void process(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if("abc".equals(username) && "123".equals(password)) {
            //跳转到首页 首页是静态资源 无首页信息  需要引用动态资源
            //使用重定向
            resp.sendRedirect("jsp/articleList.jsp");
        } else {
            throw new AppException("用户名或密码错误");
        }
    }
}
