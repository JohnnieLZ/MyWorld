package com.johnnieliu.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * JDBC 的工具类 
 *
 * 其中包含: 获取数据库连接, 关闭数据库资源等方法. 
 */
public class JDBCTools {

    public static Connection getConnection() throws Exception {
        Properties properties = new Properties();
        InputStream inStream = JDBCTools.class.getClassLoader()
                .getResourceAsStream("jdbc.properties");
        properties.load(inStream);

        // 1. 准备获取连接的 4 个字符串: user, password, url, jdbcDriver  
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url= properties.getProperty("url");
        String jdbcDriver= properties.getProperty("driverClassName");

        // 2. 加载驱动: Class.forName(driverClass)  
        Class.forName(jdbcDriver);

        // 3.获取数据库连接  
        Connection connection = DriverManager.getConnection(url, user,
                password);
//        //关闭输入流
//        inStream.close();
        return connection;
    }

    public static void releaseDB(ResultSet resultSet, Statement statement,
                                 Connection connection) {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

} 