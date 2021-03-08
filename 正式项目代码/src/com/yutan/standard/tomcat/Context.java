package com.yutan.standard.tomcat;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Context {
    private final String name;

    public Map<String, String> servletNameToServletClassNameMap = new HashMap<>();
    public LinkedHashMap<String, String> urlToServletNameMap = new LinkedHashMap<>();

    public Context(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
