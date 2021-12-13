package com.example11.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.sql.*;

public class ConnectorDB {
    public static Connection getConnection() throws SQLException {
//        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        ResourceBundle resource = ResourceBundle.getBundle("db",
                Locale.getDefault());
        String url = resource.getString("db.infodb");
        String user = resource.getString("db.root");
        String pass = resource.getString("db.root");
        return DriverManager.getConnection(url, user, pass);
    }
}