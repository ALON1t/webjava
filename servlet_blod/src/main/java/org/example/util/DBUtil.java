package org.example.util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.example.exception.AppException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {
    //数据库连接池
    //3306/servlet_blog  数据库名test -> servlet_blog
    private static final String URL = "jdbc:mysql://localhost:3306/servlet_blog?user=root&password=102917&useUnicode=true&characterEncoding=UTF-8&useSSL=false";

    private static final DataSource DS = new MysqlDataSource();

    //静态代码块
    //MysqlDataSource中才有setUrl() 方法 需要类型强转
    static {
        ((MysqlDataSource)DS).setUrl(URL);
    }
    public static Connection getConnection() {
        try{
            return DS.getConnection();
        } catch (SQLException e) {
            //e.printStackTrace(); //打印语句 异常
            //抛自定义异常
            //return null;

            throw new AppException("获取数据库连接失败",e);

        }
    }

}
