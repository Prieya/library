package com.library;

import java.sql.*;

public class genre {
    private Connection connec = null;
    private PreparedStatement statement = null;
    private ResultSet result = null;

    public Connection tryConnect(){
        final String url = "jdbc:postgresql://localhost:5432/library";
        try{
            connec = DriverManager.getConnection(url);
            System.out.println("Connection to genre was sucess");
        }catch( Exception e){
            System.out.println("Error connecting to genre " + e);
        }
        return connec;
    }

    public void AddGenre(String name){
        try{
            connec = tryConnect();
            statement = connec.prepareStatement("INSERT INTO genre name VALUE(?)");
            statement.setString(1, name);
            statement.executeUpdate();
            connec.close();
            statement.close();
        }catch(Exception e){
            System.out.println("Error add to genre " + e);
        }

    }
    public void UpdateGenre(int id, String name){
        boolean Genre_exist = false;
        try{
            connec = tryConnect();
            statement = connec.prepareStatement("SELECT * FROM genre");
            result = statement.executeQuery();
            while(result.next()){
                int Gid = result.getInt("id");
                if(Gid == id){
                    Genre_exist = true;
                }
            }
            if(Genre_exist) {
                statement = connec.prepareStatement("UPDATE genre SET name = ? WHERE id = ?");
                statement.setString(1, name);
                statement.setInt(2, id);
                statement.executeUpdate();
                connec.close();
                statement.close();
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
            connec = tryConnect();
            statement = connec.prepareStatement("SELECT * FROM genre");
            result = statement.executeQuery();
            while(result.next()){
                String Gname = result.getString("name");
                if(Gname.equalsIgnoreCase(name)){
                    Genre_Exist = true;
                }
                statement.close();
                result.close();
            }
            if(Genre_Exist){
                statement = connec.prepareStatement("DELETE FROM genre WHERE name = ?");
                statement.setString(1,name);
                statement.executeUpdate();
                statement.close();
                connec.close();
            }else{
                System.out.println("That name can not be found in genre");
            }
        }catch(Exception e){
            System.out.println("Error Deleting from genre");
        }
    }

    public int getGenreID(String name){
        String Gname = "";
        int GID = 0;
        try {
            connec = tryConnect();
            statement = connec.prepareStatement("SELECT * FROM genre");
            result = statement.executeQuery();
            while (result.next()) {
                Gname = result.getString("name");
                if (Gname.equalsIgnoreCase(name)) {
                    GID = result.getInt("id");
                }
            }
        }catch(Exception e){

        }
        return GID;
    }



}
