package orrg.example.util;

import org.example.util.DBUtil;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

public class JSONUtilTest {
    @Test
    public void test() {
        Connection c = DBUtil.getConnection();
        Assert.assertNotNull(c);
    }

    @Test
    public void testDeserialize() {

    }
}
