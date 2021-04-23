package com.library;

import com.library.Helper_Method.DoesExist;

import java.sql.*;

public class users {
    private TryConnect u = new TryConnect();
    private DoesExist e = new DoesExist();
    private Connection con = null;
    private PreparedStatement statement = null;
    private ResultSet result = null;


    public void user_adding(String email, String name, String password){
        try{
            con = u.Tryconnection();
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

    public void delete_user(int id) {
        boolean Exist = false;
        if (e.Exist("users", id)) {
            try{
                statement = con.prepareStatement("DELETE FROM users WHERE id = ?");
                statement.setInt(1, id);
                statement.executeUpdate();
                statement.close();
                con.close();
            } catch (Exception e) {
                System.out.println("Error connecting");
            }
            } else {
                System.out.println("The email or name can not be found in book_genre");
            }

    }

    public void Update_user(int id, String email, String password) {
            if (e.Exist("users", id)) {
                try{
                    statement = con.prepareStatement("UPDATE users SET email = ? AND password = ? WHERE id = ?");
                    statement.setString(1, email);
                    statement.setString(2, password);
                    statement.setInt(3, id);
                    statement.executeUpdate();
                    statement.close();
                    con.close();
                } catch (Exception e) {
                    System.out.println("Error connecting");
                }
            } else {
                System.out.println("The name can not be found in book_genre");
            }

    }

    public int getuserID(String name, String password){
        String Uname = "";
        String Upassword = "";
        int id = 0;
        try {
            con = u.Tryconnection();
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
