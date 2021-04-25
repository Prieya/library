package com.library;

import java.sql.*;

public class genre {
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

    public void DeleteGenre(String name){
        boolean Genre_Exist = false;
        try{
            connect = database_connection.Tryconnection();
            statement = connect.prepareStatement("SELECT * FROM genre");
            result = statement.executeQuery();
            while(result.next()){
                String Genre_Type = result.getString("name");
                if(Genre_Type.equalsIgnoreCase(name)){
                    Genre_Exist = true;
                }
                statement.close();
                result.close();
            }
            if(Genre_Exist){
                statement = connect.prepareStatement("DELETE FROM genre WHERE name = ?");
                statement.setString(1,name);
                statement.executeUpdate();
                connect.close();
            }else{
                System.out.println("That name can not be found in genre");
            }
        }catch(Exception e){
            System.out.println("Error Deleting from genre");
        }
    }

    public int getGenrebyID(String name){
        String Genre_name = "";
        int GenreID = 0;
        try {
            connect = database_connection.Tryconnection();
            statement = connect.prepareStatement("SELECT * FROM genre WHERE name = " + name);
            result = statement.executeQuery();
            GenreID = result.getInt("id");

        }catch(Exception e){
            System.out.println("Eroor in gteGenrebyId: " + e);
        }
        return GenreID;
    }



}
