package com.library;

import java.sql.*;

public class Author {
    private Connection conn = null;
    private PreparedStatement statement = null;
    private ResultSet result = null;

    public Connection TryConnection() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        final String url = "jdbc:postgresql:library";
        try{
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to author is success");
        }catch(SQLException e){
            System.out.println("Connection error " + e.getMessage());
        }
        return conn;
    }

    public void addAuthor(String name){
        try{
            conn = TryConnection();
            statement = conn.prepareStatement("INSERT INTO author" +
                    "(name) VALUE(?)");
            statement.setString(1, name);
            statement.executeUpdate();
            statement.close();
            conn.close();
        }catch(Exception e){
            System.out.println("Error adding author " + e);
        }
    }

    public void UpdateAuthor(int id, String name){
        try{
            conn = TryConnection();
            statement = conn.prepareStatement("UPDATE author set name ? WHERE id = ? ");
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();
            statement.close();
            conn.close();
        }catch(Exception e){
            System.out.println("Error updating author");
        }
    }

    public void deleteAuthor(String name){
        if(authorExist(name)) {
            try {
                conn = TryConnection();
                statement = conn.prepareStatement("DELETE FROM author WHERE name =?");
                statement.setString(1, name);
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
    public boolean authorExist(String name){
        boolean exist = false;
        try{
            conn = TryConnection();
            statement = conn.prepareStatement("SELECT * FROM author");
            result = statement.executeQuery();
            while(result.next()){
                String Aname = result.getString("name");
                if(name.equalsIgnoreCase(Aname)){
                    exist = true;
                }
            }
        }catch(Exception e){
        }
        return exist;
    }

    public int getauthorID(String name){
        String Aname = "";
        int AuthorID  = 0;
        try{
            conn = TryConnection();
            statement = conn.prepareStatement("SELECT * FROM author");
            result = statement.executeQuery();
            while(result.next()){
                 Aname = result.getString("name");
                if(name.equalsIgnoreCase(Aname)){
                    AuthorID = result.getInt("id");
                }
            }
        }catch(Exception e){
        }
        return AuthorID;
    }

    public String getAuthorName(int id){
        int Aid = 0;
        String Authorname = "";
        try{
            conn = TryConnection();
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