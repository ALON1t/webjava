package com.yutan.standard.tomcat;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Context {
    private final ConfigReader reader;
    private final String name;
    private Config config;

    public Context(ConfigReader reader, String name) {
        this.reader = reader;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void readConfigFile() throws IOException {
        this.config = reader.read(name);
    }
}
