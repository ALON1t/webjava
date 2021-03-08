package com.yutan.standard.http;

import com.yutan.standard.ServletRequest;

public interface HttpServletRequest extends ServletRequest {
    Cookie[] getCookies();

    String getHeader(String name);

    String getMethod();

    String getContextPath();
    String getServletPath();
    String getRequestURI();

    HttpSession getSession();

    String getParameter(String english);
}
