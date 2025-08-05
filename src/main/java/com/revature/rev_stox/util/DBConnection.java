package com.revature.rev_stox.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DBConnection {

    public static Connection getConnection() {
        try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties props = new Properties();
            props.load(input);

            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            //load jdbc driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //return new connection everytime
            return DriverManager.getConnection(url, username, password);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to get a DB connection: "+e.getMessage());
        }
    }
}
