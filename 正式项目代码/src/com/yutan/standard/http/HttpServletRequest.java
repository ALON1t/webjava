package com.yutan.standard.http;

import com.yutan.standard.ServletRequest;

public interface HttpServletRequest extends ServletRequest {
    Cookie[] getCookies();

    String getHeader(String name); //获取请求头信息

    String getMethod(); //获取方法

    String getContextPath(); //
    String getServletPath();
    String getRequestURI(); //

    HttpSession getSession();

    String getParameter(String english);
}
