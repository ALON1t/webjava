package org.example.dao;

import org.example.exception.AppException;
import org.example.model.User;
import org.example.util.DBUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {

    public static User query(String username) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = DBUtil.getConnection();
            String sql = "select id, username, password, " +
                    "nickname, sex, birthday, head from user " +
                    "where username =?";
            ps = c.prepareStatement(sql);
            //设置占位符
            ps.setString(1,username);
            rs = ps.executeQuery();
            //处理结果集
            User user = null;  //没有结果集返回null  有结果集new一个
            while(rs.next()) {
                user = new User();
                //设置User的值
                user.setId(rs.getInt("id"));
                user.setUsername(username);
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setSex(rs.getBoolean("sex"));
                //关于日期的使用
                //Java中一般时使用Java.util.Date(年月日时分秒)
                //rs.getDate() //返回值是java.sql,Date(时分秒)
                //rs.getTimestamp()  //返回值是java.sql.Timestamp(年月日时分秒)
                //rs.getDate("birthday").getTime();
                java.sql.Date birthday = rs.getDate("birthday");
                if (birthday != null) {
                    user.setBirthday(new Date(birthday.getTime()));
                }

                user.setHead(rs.getString("head"));
            }
            return user;
        } catch (Exception e) {
            throw new AppException("LOG001","查询用户操作出错",e);
        } finally {
            DBUtil.close(c,ps,rs);
        }
    }
}
