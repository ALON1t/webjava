package org.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * JSON数据格式 http请求和响应：保存在请求体和响应体里
 * 使用jackson框架来作json数据的处理
 * json是前后端作请求响应需要的在body里的数据格式
 * 使用json框架序列化和反序列化
 */
public class JSONUtil { //工具类
    private static final ObjectMapper MAPPER = new ObjectMapper();
    //序列化和反序列化
    /**
     * json序列化：把Java对象序列为json字符串
     * @param o  java对象
     * @return   json字符串
     */
    public static String serialize(Object o) {
        //writeValueAsString把一个对象写成字符串
        try {
            return MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("json序列化失败" + o);
        }
    }


    //输入流进行反序列化

    /**
     * 反序列化操作：将输入流/字符串反序列化为Java对象
     * @param is  输入流
     * @param tClass  指定要反序列化的类型
     * @param <T>
     * @return   反序列化的对象
     */
    public static <T> T deserialize(InputStream is,Class<T> tClass) { //指定一个类型进行返回，这里使用泛型
        try {
            return MAPPER.readValue(is,tClass);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("json反序列化失败" + tClass.getName());
        }

    }
}
