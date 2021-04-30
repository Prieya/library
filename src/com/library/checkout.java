package com.library;

import com.library.Helper_Method.DoesExist;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

public class checkout {
    private DoesExist exist = new DoesExist();
    private TryConnect Database_connect = new TryConnect();
    private Connection connect = null;
    private PreparedStatement statement = null;
    private ResultSet set = null;



    public void adding_checkout(int bookId, int userId){
        try{
            connect = Database_connect.Tryconnection();
            statement = connect.prepareStatement("INSERT INTO checkout(book_id, user_id) VALUES(?, ?)");
            statement.setInt(1, bookId);
            statement.setInt(2, userId);
            statement.executeQuery();
            connect.close();
        }catch(Exception e){
            System.out.println("Error adding checkout");
        }
        System.out.println("ID of checkout = " + GetCheckoutByID(bookId));
    }

    public void Delete_checkoutBook(int id){
        if (exist.Exist("checkout", id)) {
            try{
                connect = Database_connect.Tryconnection();
                statement = connect.prepareStatement("DELETE FROM checkout where id = ?");
                statement.setInt(1, id);
                statement.executeUpdate();
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
                connect = Database_connect.Tryconnection();
                statement = connect.prepareStatement("UPDATE checkout SET book_id = ? and user_id = ? where id = ?");
                statement.setInt(1, bookID);
                statement.setInt(2, userId);
                statement.setInt(3, Id);
                statement.executeUpdate();
                connect.close();
            }catch(Exception e){
                System.out.println("Error in Updating on checkout");
            }
            }else{
                System.out.println("ID wasn't found in checkout for Update");
            }
    }

    public int GetCheckoutByID(int bookID){
        int ID = 0;
        try{
            connect = Database_connect.Tryconnection();
            statement = connect.prepareStatement("SELECT * FROM WHERE bookId = ?");
            statement.setInt(1, bookID);
            set = statement.executeQuery();
            if(set.next()) {
                ID = set.getInt("id");
            }
            connect.close();
        }catch(Exception e){
            System.out.println("error int GetCheckoutById");
        }
        return ID;
    }


}
