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

@WebServlet("/articleUpdate")
public class ArticleUpdateServlet extends AbstractBaseServlet{
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //文章修改操作
        //Content-Type: application/json
        //请求数据类型是application/json  需要使用输入流获取
        InputStream is = req.getInputStream(); //反序列化为Java字符串
        Article a = JSONUtil.deserialize(is,Article.class);
        int num = ArticleDAO.update(a);
        return null;
    }
}
