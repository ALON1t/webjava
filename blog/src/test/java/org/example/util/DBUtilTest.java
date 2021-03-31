package org.example.util;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

public class DBUtilTest { //单元测试代码
    @Test
    public void testGetConnection() {
        //URL错误，测试将不通过
        //若是没有数据库blog将报错 或者密码错误等
        Connection connection = DBUtil.getConnection();
        System.out.println(connection);
        Assert.assertNotNull(connection);


    }
}
