package com.library;

import com.library.Helper_Method.DoesExist;

import java.sql.*;

public class Author {
    private TryConnect database_connection = new TryConnect();
    private DoesExist exist = new DoesExist();
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet result = null;

    public void addAuthor(String name){
        try{
            connection = database_connection.Tryconnection();
            statement = connection.prepareStatement("INSERT INTO author(name)" +
                    "VALUES(?)");
            statement.setString(1, name);
            statement.executeUpdate();
            connection.close();
        }catch(Exception e){
            System.out.println("Error adding author " + e);
        }
    }

    public void UpdateAuthor(int id, String name){
        if(exist.Exist("author", id)) {
            try {
                connection = database_connection.Tryconnection();
                statement = connection.prepareStatement("UPDATE author set name ? WHERE id = ? ");
                statement.setString(1, name);
                statement.setInt(2, id);
                statement.executeUpdate();
                connection.close();
            } catch (Exception e) {
                System.out.println("Error updating author");
            }
        }else{
            System.out.println("id does not exist  in author");
        }
    }

    public void deleteAuthor(int id){
        if(exist.Exist("author", id)) {
            try {
                connection = database_connection.Tryconnection();
                statement = connection.prepareStatement("DELETE FROM author WHERE id =?");
                statement.setInt(1, id);
                statement.executeUpdate();
                connection.close();
            } catch (Exception e) {
                System.out.println("Error deleting author");
            }
        }else{
            System.out.println("Author does not exist");
        }
    }

    public int getauthorByID(String name){
        String Aname = "";
        int AuthorID  = 0;
        try{
            connection = database_connection.Tryconnection();
            statement = connection.prepareStatement("SELECT * FROM author WHERE name = " + name);
            result = statement.executeQuery();
            AuthorID = result.getInt("id");
        }catch(Exception e){
            System.out.println("Error getting ID from author");
        }
        return AuthorID;
    }

    public String getAuthorName(int id){
        int AuthorID = 0;
        String Authorname = "";
        try{
            connection = database_connection.Tryconnection();
            statement = connection.prepareStatement("SELECT * FROM author");
            result = statement.executeQuery();
            while(result.next()){
                AuthorID = result.getInt("id");
                if(AuthorID == id){
                    Authorname = result.getString("name");
                }
            }
        }catch(Exception e){
        }
        return Authorname;
    }

}
