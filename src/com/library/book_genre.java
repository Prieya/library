package com.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class book_genre {
    private Connection connec = null;
    private PreparedStatement statement = null;
    private ResultSet result = null;

    public Connection tryConnect(){
        final String url = "jdbc:postgresql://localhost:5432/library";
        try{
            connec = DriverManager.getConnection(url);
            System.out.println("Connection to genre was sucess");
        }catch( Exception e){
            System.out.println("Error connecting to book_genre " + e);
        }
        return connec;
    }

    public void Book_Genre(int BId, int GId){
        try{
            connec = tryConnect();
            statement = connec.prepareStatement("INSERT INTO book_genre(book_id, genre_id), VALUES(?, ?)");
            statement.setInt(1, BId);
            statement.setInt(2, GId);
            statement.executeUpdate();
            connec.close();
            statement.close();
        }catch(Exception e){
            System.out.println("Error add to book_genre " + e);
        }

    }
    public void UpdateBookGenre(int id, int BId, int GId){
        boolean Genre_exist = false;
        try{
            connec = tryConnect();
            statement = connec.prepareStatement("SELECT * FROM book_genre");
            result = statement.executeQuery();
            while(result.next()){
                int BGid = result.getInt("id");
                if(BGid == id){
                    Genre_exist = true;
                }
            }
            if(Genre_exist) {
                statement = connec.prepareStatement("UPDATE book_genre SET book_id = ? AND genre_id = ? WHERE id = ?");
                statement.setInt(1, BId);
                statement.setInt(2, GId);
                statement.setInt(3, id);
                statement.executeUpdate();
                connec.close();
                statement.close();
            }else{
                System.out.println("The id wasn't found for the Book_Genre");
            }
        }catch(Exception e){
            System.out.println("Error Updating to Book_genre " + e);
        }
    }

    public void DeleteBookGenre( int BId, int GId){
        boolean Genre_Exist = false;
        try{
            connec = tryConnect();
            statement = connec.prepareStatement("SELECT * FROM book_genre");
            result = statement.executeQuery();
            while(result.next()){
                int BID = result.getInt("book_id");
                int GID = result.getInt("genre_id");
                if(BID == BId && GID == GId){
                    Genre_Exist = true;
                }
            }
            statement.close();
            if(Genre_Exist){
                statement = connec.prepareStatement("DELETE FROM book_genre WHERE BID = ? AND GID = ?");
                statement.setInt(1,BId);
                statement.setInt(2,GId);
                statement.executeUpdate();
                statement.close();
                connec.close();
            }else{
                System.out.println("The Book id or Genre id can not be found in book_genre");
            }
        }catch(Exception e){
            System.out.println("Error Deleting from book_genre");
        }
    }

    public void DeleteBookGenre( int BId){
        boolean Genre_Exist = false;
        try{
            connec = tryConnect();
            statement = connec.prepareStatement("SELECT * FROM book_genre");
            result = statement.executeQuery();
            while(result.next()){
                int BID = result.getInt("book_id");
                if(BID == BId){
                    Genre_Exist = true;
                }
            }
            statement.close();
            if(Genre_Exist){
                statement = connec.prepareStatement("DELETE FROM book_genre WHERE BID = ?");
                statement.setInt(1,BId);
                statement.executeUpdate();
                statement.close();
                connec.close();
            }else{
                System.out.println("The Book id can not be found in book_genre");
            }
        }catch(Exception e){
            System.out.println("Error Deleting from book_genre");
        }
    }

}
