package service;

import entity.Stu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 1. @ClassName StuInfoService
 * 2. @Description TODO
 * 3. @Author huo
 * 4. @Date 2024/5/16 8:53
 */

public class StuInfoService {
    public Stu selectById(String id) throws SQLException {
        String url = "jdbc:mysql:///db1?useSSL=false";
        String username = "root";
        String password = "123456";
        Connection conn = DriverManager.getConnection(url, username, password);

        // 定义sql
        String sql = "select * from stu where id = ?";

        // 获取pstmt对象
        java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);

        // 设置？的值
        pstmt.setString(1,id);

        // 执行sql
        ResultSet rs = pstmt.executeQuery();
        Stu stu = new Stu();
        while (rs.next()) {
            System.out.println("res" + rs);
            //获取数据
            String stuId = rs.getString("id");
            String name = rs.getString("name");
            String classname = rs.getString("classname");

            //封装对象
//            stu = new Stu();
            stu.setId(stuId);
            stu.setName(name);
            stu.setClassname(classname);
        }
        return stu;
    }
}
