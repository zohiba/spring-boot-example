
package com.javabunga.springbootexample.web;
import edu.uci.adcom.util.CryptoJCE_AES;
import java.sql.*;

public class ConnectionManager {
    static final String USER = "exfiles_webuser";
    static final String PASS = CryptoJCE_AES.getPee(USER);
    static String to_be_displayed = "Start ";
    static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static final String DB_URL = "jdbc:sqlserver://127.0.0.1:4430";

    public static void main(String[] args) {

    }

    public static Connection getConnection(){  Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        System.out.println("Goodbye!");
        return conn;
    }
}
