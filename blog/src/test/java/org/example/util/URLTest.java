package org.example.util;

import org.example.servlet.UEditorServlet;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

public class URLTest {
    @Test
    public void test() throws UnsupportedEncodingException {
        URL url = UEditorServlet.class.
                getClassLoader().getResource("随便/随便.txt");
        System.out.println(url.getPath());
        //
        //解码
        String path = new URLDecoder().decode(url.getPath(),"UTF-8");
        System.out.println(path);
    }
}
