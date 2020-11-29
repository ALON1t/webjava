package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class JSONUtil {
    //工具类
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * JSON序列化：将Java对象序列化为json字符串
     * @param o java对象
     * @return json字符串
     */
    public static String vserialize(Object o) {
        try {
            return MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("序列化失败" + o);
        }
    }

    /**
     * 反序列化操作：将输入流/字符串反序列化为json对象
     * @param is 输入流
     * @param clzz  指定要反序列化的类型
     * @param <T>
     * @return 反序列化的类型
     */

    public static <T> T deserialize(InputStream is,Class<T> clzz) {
        try {
            return MAPPER.readValue(is,clzz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("json反序列化失败" + clzz.getName());
        }
    }




}
