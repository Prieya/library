package com.library;

import com.library.Helper_Method.DoesExist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class book_genre {
    private TryConnect database = new TryConnect();
    private DoesExist exist = new DoesExist();
    private Connection connect = null;
    private PreparedStatement statement = null;
    private ResultSet result = null;


    public void Book_Genre(int BId, int GId){
        try{
            connect = database.Tryconnection();
            statement = connect.prepareStatement("INSERT INTO book_genre(book_id, genre_id) VALUES(?, ?)");
            statement.setInt(1, BId);
            statement.setInt(2, GId);
            statement.executeUpdate();
            connect.close();
        }catch(Exception e){
            System.out.println("Error add to book_genre " + e);
        }

    }
    public void UpdateBookGenre(int id, int BookId, int GenreId){
        if(exist.Exist("book_genre", id)) {
            try {
                connect = database.Tryconnection();
                statement = connect.prepareStatement("UPDATE book_genre SET book_id = ? AND genre_id = ? WHERE id = ?");
                statement.setInt(1, BookId);
                statement.setInt(2, GenreId);
                statement.setInt(3, id);
                statement.executeUpdate();
                connect.close();
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
                connect = database.Tryconnection();
                statement = connect.prepareStatement("DELETE FROM book_genre WHERE id = ?");
                statement.setInt(1,id);
                statement.executeUpdate();
                connect.close();
            }catch(Exception e){
                System.out.println("Error Deleting from book_genre");
            }
            }else{
                System.out.println("The Book id or Genre id can not be found in book_genre");
            }

    }

    public int GetBook_GenreByIDG(int genreId){
        int ID = 0;
        try{
            connect = database.Tryconnection();
            statement = connect.prepareStatement("SELECT * FROM book_genre WHERE genre_id = " + genreId);
            result = statement.executeQuery();
            ID = result.getInt("id");
        }catch(Exception e){
            System.out.println("Error in GETGenreByID: " + e);
        }
        return ID;
    }
    public int GetBook_GenreByIDB(int bookID){
        int ID = 0;
        try{
            connect = database.Tryconnection();
            statement = connect.prepareStatement("SELECT * FROM book_genre WHERE genre_id = " + bookID);
            result = statement.executeQuery();
            ID = result.getInt("id");
        }catch(Exception e){
            System.out.println("Error in GETGenreByID: " + e);
        }
        return ID;
    }


}
