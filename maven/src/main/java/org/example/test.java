package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            // 加载JDBC驱动程序：反射，这样调用初始化com.mysql.jdbc.Driver类，即将该类加载到JVM方法
            //区，并执行该类的静态方法块、静态属性。
            Class.forName("com.mysql.jdbc.Driver");

            //1.创建数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=102917&useUnicode=true&characterEncoding=UTF-8&useSSL=false");
            System.out.println(connection);

            //2.创建操作命令对象Statement
            statement = connection.createStatement();
            //3.执行sql
            String sql = "select id,sn,name,qq_mail,classes_id from student";
            resultSet = statement.executeQuery(sql);

            List<Student> studentList = new ArrayList<>();
            //4.处理结果集（查询操作）
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int sn = resultSet.getInt("sn");
                String name = resultSet.getString("name");
                String qq_mail = resultSet.getString("qq_mail");
                int classes_id = resultSet.getInt("classes_id");
                System.out.println(String.format("student: id=%d ,sn=%d, name=%s, qq_mail=%s, classes_id=%d", id, sn, name, qq_mail, classes_id));
                Student student = new Student();
                student.setId(id);
                //student.id = id;
                student.setSn(sn);
                student.setName(name);
                student.setQq_mail(qq_mail);
                student.setClasses_id(classes_id);
                studentList.add(student);
            }
            System.out.println(studentList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5.释放资源
            try {
                //connection.close();
                if(resultSet != null) {
                    resultSet.close();
                }
                if(statement != null) {
                    statement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    //一般来说，对象中的属性不提供对外直接修改
    //1.getter 和 setter 方法
    //2.构造方法来设置
    private  static class Student{//内部类
        private Integer id;
        private  Integer sn;
        private String name;
        private String qq_mail;
        private Integer classes_id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getSn() {
            return sn;
        }

        public void setSn(Integer sn) {
            this.sn = sn;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getQq_mail() {
            return qq_mail;
        }

        public void setQq_mail(String qq_mail) {
            this.qq_mail = qq_mail;
        }

        public Integer getClasses_id() {
            return classes_id;
        }

        public void setClasses_id(Integer classes_id) {
            this.classes_id = classes_id;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", sn=" + sn +
                    ", name='" + name + '\'' +
                    ", qq_mail='" + qq_mail + '\'' +
                    ", classes_id=" + classes_id +
                    '}';
        }
    }

}
