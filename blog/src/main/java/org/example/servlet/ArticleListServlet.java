package org.example.servlet;

import org.example.dao.ArticleDAO;
import org.example.exception.AppException;
import org.example.model.Article;
import org.example.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebServlet("/articleList")  //访问的后端接口
public class ArticleListServlet extends AbstractBaseServlet{
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取session，如果没有就直接抛自定义异常
        HttpSession session = req.getSession(false);
        //filter过滤器实现之后    不会报空指针错误
//        if (session == null) {
//            throw new AppException("ART002","用户没有登录，不允许访问");
//        }
        //获取登陆时创建的session保存的用户信息
        User user = (User)session.getAttribute("user");
//        if (user == null) {
//            throw new AppException("ART003","会话异常，请重新登录");
//        }
        //用户已登录，并且保存了用户信息
        List<Article> articles = ArticleDAO.queryByUserId(user.getId());
        return articles;
    }
}
