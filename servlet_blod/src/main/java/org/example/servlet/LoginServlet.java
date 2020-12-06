package org.example.servlet;

import org.example.dao.LoginDAO;
import org.example.exception.AppException;
import org.example.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends AbstractBase{
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        //1.获取请求数据
        //req.getParameter 获取数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = LoginDAO.query(username);
        if(user == null) {
            throw new AppException("LOG002","用户不存在");
        }
        if(!user.getPassword().equals(password))
            throw new AppException("LOG003","用户名或密码错误");

        //登录成功，创建Session
        HttpSession session = req.getSession();
        session.setAttribute("user",user);
        return null;

//        if("abc".equals(username)) { //模拟用户名密码校验
//            return null;
//            //resp.sendRedirect("jsp/articleList.jsp");
//        } else {
//            throw new AppException("LOG001","用户名或密码错误");
//        }

//        if("abc".equals(username) && "123".equals(password)) {
//            //跳转到首页 首页是静态资源 无首页信息  需要引用动态资源
//            //使用重定向
//            resp.sendRedirect("jsp/articleList.jsp");
//        } else {
//            //父类里抛出异常 try  catch  _AbstractBase
//            throw new AppException("用户名或密码错误");
//        }
    }
}
