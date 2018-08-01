package com.revature.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil
{
    public static Connection getConnection() throws SQLException, IOException
    {
//        System.out.println(new File(".").getAbsolutePath());
        Properties prop = new Properties();
        InputStream in = new FileInputStream("../webapps/reservation-app/WEB-INF/connection.properties"); //"../webapps/reservation-app/connection.properties");
        prop.load(in);

        String url = prop.getProperty("url");
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");

        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        return DriverManager.getConnection(url, user, password);
    }
}
