package com.library;

import com.library.Helper_Method.DoesExist;

import java.sql.*;

public class users {
    private TryConnect database_connection = new TryConnect();
    private DoesExist exist = new DoesExist();
    private Connection connect = null;
    private PreparedStatement statement = null;
    private ResultSet result = null;


    public void user_adding(String email, String name, String password){
        try{
            connect = database_connection.Tryconnection();
            statement = connect.prepareStatement("INSERT INTO users(email, name, password) VALUES(?, ?, ?)");
            statement.setString(1, email);
            statement.setString(2, name);
            statement.setString(3, password);
            statement.executeQuery();
            connect.close();
            statement.close();
        }catch(Exception e){
            System.out.println("Error Adding user");
        }
    }

    public void delete_user(int id) {
        boolean Exist = false;
        if (exist.Exist("users", id)) {
            try{
                connect = database_connection.Tryconnection();
                statement = connect.prepareStatement("DELETE FROM users WHERE id = ?");
                statement.setInt(1, id);
                statement.executeUpdate();
                connect.close();
            } catch (Exception e) {
                System.out.println("Error connecting");
            }
            } else {
                System.out.println("The email or name can not be found in book_genre");
            }

    }

    public void Update_user(int id, String email, String password) {
            if (exist.Exist("users", id)) {
                try{
                    connect = database_connection.Tryconnection();
                    statement = connect.prepareStatement("UPDATE users SET email = ? AND password = ? WHERE id = ?");
                    statement.setString(1, email);
                    statement.setString(2, password);
                    statement.setInt(3, id);
                    statement.executeUpdate();
                    connect.close();
                } catch (Exception e) {
                    System.out.println("Error connecting");
                }
            } else {
                System.out.println("The name can not be found in book_genre");
            }

    }

    public int GetUserByID(String name){
        int id = 0;
        try {
            connect = database_connection.Tryconnection();
            statement = connect.prepareStatement("Select * FROM users WHERE name = ?");
            statement.setString(1, name);
            result = statement.executeQuery();
            id = result.getInt("id");
        }catch(Exception e){
            System.out.println("Error getting user Id " + e);
        }
        return id;
    }
}
