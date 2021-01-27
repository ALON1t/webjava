package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Result;
import org.example.util.Index;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//根据前端请求路径，定义后端服务路径
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        //初始化工作：先构建正排索引，再根据正排构建倒排
        Index.buildForwardIndex();
        Index.buildInvertedIndex();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");//ajax请求，响应Json格式
        //构造返回给前端的内容：使用对象，之后再序列化为json格式
        Map<String,Object> map = new HashMap<>();
        //解析请求数据
        String query = req.getParameter("query");//搜索框内容
        List<Result> results = new ArrayList<>();
        try{
            //根据搜索内容处理搜索业务
            //1.根据搜索内容，进行分词，遍历每个分词
            //2.每个分词，在倒排中查找对应的文档（一个分词对应多个文档）
            //3.一个文档转化为一个Result（不同分词可能存在相同文档，需要合并）
            //4.合并完成后对List<Result>排序：权重降序排序
            map.put("ok",true);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("ok",false);
        }
        PrintWriter pw = resp.getWriter();//获取输出流
        //设置响应体内容：map对象序列化为json字符串
        pw.println(new ObjectMapper().writeValueAsString(map));
    }
}
