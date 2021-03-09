package com.yutan.standard.http;

import com.yutan.standard.ServletResponse;

public interface HttpServletResponse extends ServletResponse {
    void addCookie(Cookie cookie);

    void sendError(int sc);

    void sendRedirect(String location);

    void setHeader(String name, String value);

    void setStatus(int sc); //设置状态码

    void setContentType(String s);
}
