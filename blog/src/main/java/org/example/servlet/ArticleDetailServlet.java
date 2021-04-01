package org.example.servlet;

import org.example.dao.ArticleDAO;
import org.example.model.Article;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/articleDetail")
public class ArticleDetailServlet extends AbstractBaseServlet{
    //获取文章资源  --》修改操作时需要在原来的文章上进行修改，这就需要获取之前文章的标题 内容等
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //解析请求数据
        String id = req.getParameter("id");
        Article a = ArticleDAO.query(Integer.parseInt(id));
        //返回文章对象
        return a;
    }
}
