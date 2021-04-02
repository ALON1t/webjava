package org.example.filter;

import org.example.model.JSONResponse;
import org.example.util.JSONUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

//使用filter实现统一的资源过滤  如用户会话的统一管理，编码格式统一设置等
//使用方式：如果不满足条件，就不执行doFilter,不往下执行（跳转页面或者直接输出内容）

//配置用户统一会话管理的过滤器，使用/*匹配所有的请求路径
//服务端资源（servlet）： 只有/login不用校验session，其他都需要校验，如果不通过，返回401，响应内容随便
//前端资源（webapp）：/jsp/校验session，不通过重定向到登录页面
//                 /js/   /static/  /view/ 全部都不需要校验
@WebFilter("/*")
public class LoginFilter implements Filter {//用户登录的过滤器 来过滤用户没有登录的情况
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 每次http请求匹配到过滤器路径时，会执行该过滤器的doFilter方法
     * 如果往下执行，是调用filterChain.doFilter(request,response)
     *否则自行处理响应
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //获取当前请求的服务路径
        String servletPath = req.getServletPath();
        //不需要登录允许的访问的先做处理  往下继续调用
        if (servletPath.startsWith("/js/") || servletPath.startsWith("/static/") || servletPath.startsWith("/view/")
            || servletPath.equals("/login")) {
             filterChain.doFilter(servletRequest,servletResponse);
        } else { //校验session
            //先获取session对象，没有就返回空
            HttpSession session = req.getSession(false);
            //验证用户是否登录，如果没有登录，还需要根据前端或者后端作不同处理
            if(session == null || session.getAttribute("user") == null) {
                //前端重定向到登陆页面
                //请求/jsp/articleList.jsp,没有登录需要重定向 --》/view/login.html
                //    filter相对路径的写法：../view/login.html
                //请求/home.html(假设的前端资源) ，没有登录需要重定向
                //    filter中相对路径的写法：view/login.html
                if (servletPath.startsWith("/jsp")) {
                    //绝对路径
                    //通过servletRequest自己动态获取路径
                    resp.sendRedirect(basePath(req) + "/view/login.html"); //重定向
                } else { //后端资源返回401状态码
                    resp.setStatus(401); //设置状态码
                    //设置编码格式
                    resp.setCharacterEncoding("UTF-8");
                    //设置响应体格式
                    resp.setContentType("application/json");
                    //返回统一的json数据格式
                    JSONResponse json = new JSONResponse();
                    json.setCode("LOG000");
                    json.setMessage("用户没有登录，不允许访问");
                    PrintWriter pw = resp.getWriter();
                    //把json往输出响应体里面打印  先序列化为json格式
                    pw.println(JSONUtil.serialize(json)); //序列化
                    pw.flush();
                    pw.close();
                }
            } else {  //敏感资源，但是已经登录 允许继续执行
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }
        //通过服务路径去验证当前抢的请求到底是哪一个服务路径
    }

    //根据http请求，动态获取范文路径（服务路径[这里是blog]之前的部分）
    public static String basePath(HttpServletRequest req) {
        String schema = req.getScheme(); //获取http
        String host = req.getServerName();//获取主机IP或域名
        int port = req.getServerPort(); //获取服务器（tomcat）端口号
        String contextPath = req.getContextPath();//获取应用上下文路径
        return schema + "://" + host + ":" + port + contextPath; //拼接路径
    }


    @Override
    public void destroy() {

    }

}
