package org.example.dao;

import org.example.exception.AppException;
import org.example.model.Article;
import org.example.util.DBUtil;
import sun.security.pkcs11.Secmod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {
    public static List<Article> queryByUserId(Integer userId) {
        List<Article> articles = new ArrayList<>(); //用户已经登陆
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = DBUtil.getConnection();
            String sql = "select id,title from article where user_id=?";
            ps = c.prepareStatement(sql);
            //设置占位符
            ps.setInt(1,userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Article a = new Article();
                //结果集取值设置到文章对象
                a.setId(rs.getInt("id"));
                a.setTitle(rs.getString("title"));
                articles.add(a);
            }
            return articles;
        } catch (Exception e) {
            throw new AppException("ART001","查询文章列表出错",e);
        } finally {
            DBUtil.close(c,ps,rs); //释放资源
        }
    }

    public static int delete(String[] split) {
        Connection c = null;
        PreparedStatement ps = null;
        //不用处理结果集
        try{
            c = DBUtil.getConnection();
            StringBuilder sql = new StringBuilder("delete from article where id in(");
            for (int i = 0; i < split.length;i++) {
                if (i != 0) {
                    sql.append(",");
                }
                sql.append("?");
            }
            sql.append(")");
            //String sql = "delete from article where id in(1,2,3)";
            //sql+="";  -->new StringBuilder(sql).append("").toString();
            //ps = c.prepareStatement(sql);
            //StringBuilder:线程不安全的，但是效率更高
            //StringBuffer:线程安全的，但是效率较低
            ps = c.prepareStatement(sql.toString());
            //设置占位符的值
            for (int i = 0; i < split.length;i++) {
                //占位符的设置从1开始，数组的坐标从0开始
                ps.setInt(i + 1,Integer.parseInt(split[i])); //字符串转整型
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            throw new AppException("ART004","文章删除操作出错",e);
        } finally {
            DBUtil.close(c,ps);
        }
    }

    public static int insert(Article a) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = DBUtil.getConnection();
            String sql = "insert into article(title,content,user_id)" +
                    "value (?,?,?)";
            ps = c.prepareStatement(sql);
            //设置占位符
            ps.setString(1,a.getTitle());
            ps.setString(2,a.getContent());
            ps.setInt(3,a.getUserId());
            return ps.executeUpdate();

        } catch (Exception e) {
            throw new AppException("ART005","新增文章操作出错",e);
        } finally {
            DBUtil.close(c,ps);
        }
    }

    public static Article query(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null; //返回结果集
        try {
            c = DBUtil.getConnection();
            String sql = "select id,title,content from article where id=?";
            ps = c.prepareStatement(sql);
            //替换占位符
            ps.setInt(1,id);
            rs = ps.executeQuery();
            Article a = null;
            while (rs.next()) {
                a = new Article();
                //根据结果集设置文章属性
                a.setId(rs.getInt("id"));
                a.setTitle(rs.getString("title"));
                a.setContent(rs.getString("content"));
            }
            return a;
        } catch (Exception e) {
            throw new AppException("","",e);
        } finally {
            DBUtil.close(c,ps,rs);
        }
    }

    public static int update(Article a) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = DBUtil.getConnection();
            String sql = "update article set title=?,content=? where id=?";
            ps = c.prepareStatement(sql);
            //设置占位符
            ps.setString(1,a.getTitle());
            ps.setString(2,a.getContent());
            ps.setInt(3,a.getId());
            return ps.executeUpdate();
        } catch (Exception e) {
            throw new AppException("ART007","修改文章操作出错",e);
        } finally {
            DBUtil.close(c,ps);
        }
    }
}
