package org.example.servlet;

import org.example.dao.ArticleDAO;
import org.example.model.Article;
import org.example.model.User;
import org.example.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;

@WebServlet("/articleAdd")
public class ArticleADDServlet extends AbstractBase{
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession(false); //获取session
        User user = (User)session.getAttribute("user"); //强制转换为User
        //请求的数据类型式application/json，需要使用输入流获取
        InputStream is = req.getInputStream();
        Article a = JSONUtil.deserialize(is,Article.class);
        a.setUser_id(user.getId());
        int num = ArticleDAO.insert(a);

        return null;
    }
}
