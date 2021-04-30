package com.library;

import com.library.Helper_Method.DoesExist;

import java.sql.*;

public class genre {
    private DoesExist exist = new DoesExist();
    private TryConnect database_connection = new TryConnect();
    private Connection connect = null;
    private PreparedStatement statement = null;
    private ResultSet result = null;


    public void AddGenre(String name){
        try{
            connect = database_connection.Tryconnection();
            statement = connect.prepareStatement("INSERT INTO genre(name) VALUES(?)");
            statement.setString(1, name);
            statement.executeUpdate();
            connect.close();
        }catch(Exception e){
            System.out.println("Error add to genre " + e);
        }
        System.out.println("ID for genre = " + getGenrebyID(name));

    }
    public void UpdateGenre(int id, String name){
        boolean Genre_exist = false;
        try{
            connect = database_connection.Tryconnection();
            statement = connect.prepareStatement("SELECT * FROM genre");
            result = statement.executeQuery();
            while(result.next()){
                int GenreID = result.getInt("id");
                if(GenreID == id){
                    Genre_exist = true;
                }
            }
            if(Genre_exist) {
                statement = connect.prepareStatement("UPDATE genre SET name = ? WHERE id = ?");
                statement.setString(1, name);
                statement.setInt(2, id);
                statement.executeUpdate();
                connect.close();
            }else{
                System.out.println("The id wasn't found for the Genre");
            }
        }catch(Exception e){
            System.out.println("Error Updating to genre " + e);
        }
    }

    public void DeleteGenre(int id){
        if(exist.Exist("genre", id)) {
            try {
                connect = database_connection.Tryconnection();
                statement = connect.prepareStatement("DELETE FROM genre WHERE id = ?");
                statement.setInt(1, id);
                statement.executeUpdate();
                connect.close();
            } catch (Exception e) {
                System.out.println("Error Deleting from genre" + e);
            }
        }

    }

    public int getGenrebyID(String name){
        String Genre_name = "";
        int GenreID = 0;
        try {
            connect = database_connection.Tryconnection();
            statement = connect.prepareStatement("SELECT * FROM genre WHERE name = ?");
            statement.setString(1, name);
            result = statement.executeQuery();
            if(result.next()){
                GenreID = result.getInt("id");
            }
            connect.close();
        }catch(Exception e){
            System.out.println("Erorr in gteGenrebyId: " + e);
        }
        return GenreID;
    }



}
