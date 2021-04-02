package org.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ueditor富文本编辑器图片上传
 * 1.修改idea中tomcat配置的应用上下文路径，maven中的finalName
 * 2.修该webapp/static/ueditor/ueditor.config.js，33行修改
 *   (应用上下文路径 + 服务路径)
 * 3.实现后端接口（和第二步中的服务路径一致）
 */
@WebServlet("/ueditor")
public class UEditorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
