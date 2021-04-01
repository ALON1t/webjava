package org.example.servlet;

import org.example.dao.ArticleDAO;
import org.example.model.Article;
import org.example.model.User;
import org.example.util.JSONUtil;

import javax.jws.soap.SOAPBinding;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;

@WebServlet("/articleAdd")
public class ArticleAddServlet extends AbstractBaseServlet{
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession(false);  //从session里取用户
        User user = (User)session.getAttribute("user");
        //请求数据类型是application/json  需要使用输入流获取
        InputStream is = req.getInputStream(); //反序列化为Java字符串
        Article a = JSONUtil.deserialize(is,Article.class);
        a.setUserId(user.getId()); //设置用户id
        int num = ArticleDAO.insert(a); //插数据 插入id,title user_id(外键)
        return null;
    }
}
