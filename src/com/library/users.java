package com.library;

import java.sql.*;

public class users {
    private Connection con = null;
    private PreparedStatement statement = null;
    private ResultSet result = null;

    public Connection connect(){
        final String url = "jdbc:postgresql:library";
        try{
            con = DriverManager.getConnection(url);
            System.out.println("connection was sucess");
        }catch(Exception e){
            System.out.println("error connecting to users");
        }
        return con;
    }
    public void user_adding(String email, String name, String password){
        try{
            con = connect();
            statement = con.prepareStatement("INSERT INTO users(email, name, password) VALUES(?, ?, ?)");
            statement.setString(1, email);
            statement.setString(2, name);
            statement.setString(3, password);
            statement.executeQuery();
            con.close();
            statement.close();
        }catch(Exception e){
            System.out.println("Error Adding user");
        }
    }

    public void delete_user(String name, String email) {
        boolean Exist = false;
        try {
            con = connect();
            statement = con.prepareStatement("Select * FROM users");
            result = statement.executeQuery();
            while (result.next()) {
                String NAME = result.getString("name");
                String EMAIL = result.getString("email");
                if (NAME.equalsIgnoreCase(name) && EMAIL.equalsIgnoreCase(email)) {
                    Exist = true;
                }
            }
            statement.close();
            if (Exist) {
                statement = con.prepareStatement("DELETE FROM users WHERE email = ? AND name = ?");
                statement.setString(1, email);
                statement.setString(2, name);
                statement.executeUpdate();
                statement.close();
                con.close();
            } else {
                System.out.println("The email or name can not be found in book_genre");
            }
        } catch (Exception e) {
            System.out.println("Error connecting");
        }
    }

    public void Update_user(String name, String email, String password) {
        boolean Exist = false;
        try {
            con = connect();
            statement = con.prepareStatement("Select * FROM users");
            result = statement.executeQuery();
            while (result.next()) {
                String NAME = result.getString("name");
                if (NAME.equalsIgnoreCase(name)) {
                    Exist = true;
                }
            }
            statement.close();
            if (Exist) {
                statement = con.prepareStatement("UPDATE users SET email = ? AND password = ? WHERE name = ?");
                statement.setString(1, email);
                statement.setString(2, password);
                statement.setString(3, name);
                statement.executeUpdate();
                statement.close();
                con.close();
            } else {
                System.out.println("The name can not be found in book_genre");
            }
        } catch (Exception e) {
            System.out.println("Error connecting");
        }
    }

    public int getuserID(String name, String password){
        String Uname = "";
        String Upassword = "";
        int id = 0;
        try {
            con = connect();
            statement = con.prepareStatement("Select * FROM users");
            result = statement.executeQuery();
            while (result.next()) {
                Uname = result.getString("name");
                Upassword = result.getString("password");
                if (Uname.equalsIgnoreCase(name) && Upassword.equals(password)) {
                    id = result.getInt("id");
                }
            }

        }catch(Exception e){
            System.out.println("Error getting user Id");
        }
        return id;
    }
}
