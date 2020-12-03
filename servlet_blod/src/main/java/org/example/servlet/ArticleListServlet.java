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

@WebServlet("/articleList")
public class ArticleListServlet extends AbstractBase{
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession(false);
        //boolean notlogin = false;
        if(session == null)
            throw new AppException("ART002","用户未登录，不允许访问");
        //获取登陆时创建的session保存用户信息
        User user = (User) session.getAttribute("user");
        if(user == null)
            throw new AppException("ART003","会话异常，请重新登录");
        //用户已登陆，并且保存了用户信息
        List<Article> articles = ArticleDAO.queryByUserId(user.getId());
        return articles;
    }
}
