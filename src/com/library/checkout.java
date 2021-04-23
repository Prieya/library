package com.library;

import com.library.Helper_Method.DoesExist;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

public class checkout {
    private DoesExist exist = new DoesExist();
    private TryConnect C = new TryConnect();
    private Connection connect = null;
    private PreparedStatement statement = null;
    private ResultSet set = null;



    public void adding_checkout(int bookId, int userId){
        try{
            connect = C.Tryconnection();
            statement = connect.prepareStatement("INSERT INTO checkout(book_id, user_id) VALUES(?, ?)");
            statement.setInt(1, bookId);
            statement.setInt(2, userId);
            statement.executeQuery();
            statement.close();
            connect.close();
        }catch(Exception e){
            System.out.println("Error adding checkout");
        }
    }

    public void Delete_checkoutBook(int id){
        if (exist.Exist("checkout", id)) {
            try{
                statement = connect.prepareStatement("DELETE FROM checkout where id = "+ id);
                statement.executeUpdate();
                statement.close();
                connect.close();
            }catch(Exception e){
                System.out.println("Error in Deleting on checkout");
            }
            }else{
                System.out.println("Book ID wasn't found in checkout to be deleted");
            }

    }



    public void Update_checkout(int Id, int bookID, int userId){
        if (exist.Exist("checkout", Id)) {
            try{
                statement = connect.prepareStatement("UPDATE checkout SET book_id = ? and user_id = ? where id = ?");
                statement.setInt(1, bookID);
                statement.setInt(2, userId);
                statement.setInt(3, Id);
                statement.executeUpdate();
                statement.close();
                connect.close();
            }catch(Exception e){
                System.out.println("Error in Updating on checkout");
            }
            }else{
                System.out.println("ID wasn't found in checkout for Update");
            }

    }

}
