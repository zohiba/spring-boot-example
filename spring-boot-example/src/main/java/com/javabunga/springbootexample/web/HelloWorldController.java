package com.javabunga.springbootexample.web;
import java.sql.*;
import edu.uci.adcom.util.CryptoJCE_AES;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@RestController

public class HelloWorldController {

    //  Database credentials


    //getting password from the given username




    @RequestMapping("/hello")
    public String sayHello(@RequestParam(value = "name") String name) {
        return "Hello 12"+ name + "!";
    }

    @RequestMapping("/hello/home")
    public String sayHome(@RequestParam(value = "home") String name) {
    return "Home mapping" + name + "!";
    }


    @RequestMapping("/contact")
    public String cotact() {
        return getContact();
    }

    @RequestMapping("/info")
    public String info(@RequestParam(value = "name") String name) {
        return getInfo(name);
    }

    private String getInfo(String input_name){
        Connection conn = null;
        Statement stmt = null;
        String to_be_displayed = "";
        try {
            //STEP 2: Register JDBC driver
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = ConnectionManager.getConnection();

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            String ucinetid = input_name;
            sql = "SELECT * FROM dbo.contact where s_ucinetid = ?";
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setString(1, ucinetid);


            ResultSet rs = prep.executeQuery();
            //ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                String name = rs.getString("s_name");
                String email = rs.getString("s_email");
                to_be_displayed+=name +" ";
                to_be_displayed+= email+ " ";


                //Display values
                System.out.print("Name: " + name);
                System.out.print(", Email: " + email);

            }
            System.out.print(to_be_displayed);

            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try


        return to_be_displayed;
    }
    private String getContact(){
        String to_be_displayed = "Start ";
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = ConnectionManager.getConnection();

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT l_contact_seq, s_ucinetid FROM dbo.contact";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("l_contact_seq");
                String first = rs.getString("s_ucinetid");
                to_be_displayed+=id +" ";
                to_be_displayed+= first+ " ";


                //Display values
                System.out.print("ID: " + id);
                System.out.print(", First: " + first);

            }
            //System.out.print(to_be_displayed);

            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return to_be_displayed;

    }



}