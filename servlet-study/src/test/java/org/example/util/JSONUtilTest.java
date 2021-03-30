package org.example.util;

import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

//单元测试代码  src/test/java中
public class JSONUtilTest {

    //单元测试：junit框架使用方法上@Test注解，保证方法为public void
    @Test
    public void testSerialize() {
        //测试序列化操作：使用map模拟复杂
        Map map = new HashMap();
        map.put("name","java");
        map.put("student",new int[]{1,2});  //定义数组
        String json = JSONUtil.serialize(map);
        System.out.println(json);
        //Assert  断言
        Assert.assertNotNull(json);

    }

    //测试反序列化
    @Test
    public void testDeserialize() {
        //类加载器加载某个资源，返回输入流
        InputStream is = JSONUtilTest.class.getClassLoader()
                .getResourceAsStream("login.json");
        Map map = JSONUtil.deserialize(is,HashMap.class);
        System.out.println(map);
        Assert.assertNotNull(map);
    }
    /**
     * 代理服务器：
     * 1. 正向代理服务器：抓包工具
     * 2. 反向代理服务器：nginx
     */
}
