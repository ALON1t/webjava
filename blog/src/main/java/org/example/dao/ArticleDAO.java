package org.example.dao;

import org.example.exception.AppException;
import org.example.model.Article;
import org.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 例如，在登陆之后发起一个post请求，请求访问articleAdd
 * POST /blog/articleAdd HTTP/1.1
 * Host: localhost:8080
 * Content-Type: application/json
 * Cookie: JSESSIONID=EDED04E97199C8E007819DB4B487EB13
 * Content-Length: 65
 *
 * {
 *     "title":"测试一下",
 *     "content":"hahah",
 *     "aa":"bbb"   //Article中没有aa这个键
 * }
 *
 * 流程：显示通过客户端（可以使用postman、浏览器等）发起一个http请求，
 *      找到对应的主机 + 进程号，找到对应服务器程序（可能部署有多个应用），
 *      再根据应用上下文路径找到对应的应用，
 *      再根据服务路径找服务（找到一个Servlet类）
 *      根据 POST / GET 找具体方法  -->doXXX()
 *      再执行
 *
 *
 * 客户端发起http请求： POST/blog/articleAdd HTTP/1.1
 *    1.找服务
 *    2.找服务方法
 *    3.服务方法执行
 *       （1） 校验请求数据，解析请求数据
 *       （2） 业务处理：业务逻辑+数据库CRUD
 *       （3） 返回响应数据  通常查询需要返回业务数据，更新操作只需要返回是否操作成功
 *            返回 application/json
 */

/**
 * 数据库jdbc操作
 * 1.获取数据库连接（使用同一个DataSource对象获取）
 *    设置一个带？占位符的SQL语句
 * 2.创建操作命令对象：PreparedStatement预编译的操作命令对象
 * 3.替换占位符：ps.setXXX(占位符索引，要替换的值)  值的类型决定使用的set.XXX方法
 *    ps.setInt()  ps.setString()
 * 4.执行SQL语句
 *   更新操作（插入、修改、删除）
 *       int n = ps.executeUpdate();成功操作了几条数据
 *   查询操作
 *       ResultSet rs = ps.executeQuery();  返回结果集对象
 *       处理结果集：Article a = null;
 *                 while (rs.next()) { //结果集移动到下一行，返回是否有值（循环处理每一行数据，一行处理一个对象，多行处理List<对象>）
 *                 a = new Article();
 *                 //根据结果集设置文章属性
 *                 a.setId(rs.getInt("id"));  //结果集映射（结果集转换为Java对象）：每一行的表头转换为对象中的属性
 *                 a.setTitle(rs.getString("title")); //rs.getXXX 转换列的值，再通过 a.setXXX 设置到Java对象属性中
 *                 a.setContent(rs.getString("content"));
 *              }
 * 4.释放资源(反向释放)： 结果集对象.close()
 *                    操作命令对象.close()
 *                    数据库连接.close()
 */
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
