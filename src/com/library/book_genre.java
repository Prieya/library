package com.library;

import com.library.Helper_Method.DoesExist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class book_genre {
    private TryConnect g = new TryConnect();
    private DoesExist exist = new DoesExist();
    private Connection connec = null;
    private PreparedStatement statement = null;
    private ResultSet result = null;


    public void Book_Genre(int BId, int GId){
        try{
            connec = g.Tryconnection();
            statement = connec.prepareStatement("INSERT INTO book_genre(book_id, genre_id) VALUES(?, ?)");
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
        if(exist.Exist("book_genre", id)) {
            try {
                statement = connec.prepareStatement("UPDATE book_genre SET book_id = ? AND genre_id = ? WHERE id = ?");
                statement.setInt(1, BId);
                statement.setInt(2, GId);
                statement.setInt(3, id);
                statement.executeUpdate();
                connec.close();
                statement.close();
            }catch(Exception e){
                System.out.println("Error Updating to Book_genre " + e);
            }
            }else{
                System.out.println("The id wasn't found for the Book_Genre");
            }

    }

    public void DeleteBookGenre( int id){
        if(exist.Exist("book_genre", id)) {
            try {
                statement = connec.prepareStatement("DELETE FROM book_genre WHERE id = ?");
                statement.setInt(1,id);
                statement.executeUpdate();
                statement.close();
                connec.close();
            }catch(Exception e){
                System.out.println("Error Deleting from book_genre");
            }
            }else{
                System.out.println("The Book id or Genre id can not be found in book_genre");
            }

    }



}
