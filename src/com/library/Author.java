package com.library;

import com.library.Helper_Method.DoesExist;

import java.sql.*;

public class Author {
    private TryConnect c = new TryConnect();
    private DoesExist exist = new DoesExist();
    private Connection conn = null;
    private PreparedStatement statement = null;
    private ResultSet result = null;

    public void addAuthor(String name){
        try{
            conn = c.Tryconnection();
            statement = conn.prepareStatement("INSERT INTO author(name)" +
                    "VALUES(?)");
            statement.setString(1, name);
            statement.executeUpdate();
            statement.close();
            conn.close();
        }catch(Exception e){
            System.out.println("Error adding author " + e);
        }
    }

    public void UpdateAuthor(int id, String name){
        if(exist.Exist("author", id)) {
            try {
                conn = c.Tryconnection();
                statement = conn.prepareStatement("UPDATE author set name ? WHERE id = ? ");
                statement.setString(1, name);
                statement.setInt(2, id);
                statement.executeUpdate();
                statement.close();
                conn.close();
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
                conn = c.Tryconnection();
                statement = conn.prepareStatement("DELETE FROM author WHERE id =?");
                statement.setInt(1, id);
                statement.executeUpdate();
                statement.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("Error deleting author");
            }
        }else{
            System.out.println("Author does not exist");
        }
    }

    public int getauthorID(String name){
        String Aname = "";
        int AuthorID  = 0;
        try{
            conn = c.Tryconnection();
            statement = conn.prepareStatement("SELECT * FROM author");
            result = statement.executeQuery();
            while(result.next()){
                 Aname = result.getString("name");
                if(name.equalsIgnoreCase(Aname)){
                    AuthorID = result.getInt("id");
                }
            }
        }catch(Exception e){
            System.out.println("Error getting ID from author");
        }
        return AuthorID;
    }

    public String getAuthorName(int id){
        int Aid = 0;
        String Authorname = "";
        try{
            conn = c.Tryconnection();
            statement = conn.prepareStatement("SELECT * FROM author");
            result = statement.executeQuery();
            while(result.next()){
                Aid = result.getInt("id");
                if(Aid == id){
                    Authorname = result.getString("name");
                }
            }
        }catch(Exception e){
        }
        return Authorname;
    }

}
