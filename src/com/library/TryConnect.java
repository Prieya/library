package com.library;

import java.sql.*;

public class TryConnect {
    private Connection connection;

    public Connection Tryconnection(){
        final String url = "jdbc:postgresql://localhost:5432/library";
        try{
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to Database was success");
        }catch(SQLException e){
            System.out.println("Connection error" + e);
        }
        return connection;
    }


}