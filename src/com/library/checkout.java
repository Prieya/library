package com.library;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

public class checkout {
    private Connection connect = null;
    private PreparedStatement statement = null;
    private ResultSet set = null;

    public Connection tryConnect(){
        final String url = "jdbc:postgresql://localhost:5432/library";
        try{
            connect = DriverManager.getConnection(url);
            System.out.println("Connecting on checkout");
        }catch(Exception e){
            System.out.println("Error connecting on checkout");
        }
        return connect;
    }

    public void adding_checkout(int bookId, int userId){
        try{
            connect = tryConnect();
            statement = connect.prepareStatement("INSERT INTO checkout VALUE(?, ?)");
            statement.setInt(1, bookId);
            statement.setInt(2, userId);
            statement.executeQuery();
            statement.close();
            connect.close();
        }catch(Exception e){
            System.out.println("Error adding checkout");
        }
    }

    public void Delete_checkoutBook(int BId){
        boolean check = false;
        try{
            connect = tryConnect();
            statement = connect.prepareStatement("SELECT * FROM checkout");
            set = statement.executeQuery();
            while(set.next()){
                int id = set.getInt("book_id");
                if(id == BId){
                    check= true;
                }
            }
            statement.close();
            if(check){
                statement = connect.prepareStatement("DELETE FROM checkout where book_id = BId");
                statement.executeUpdate();
                statement.close();
                connect.close();
            }else{
                System.out.println("Book ID wasn't found in checkout to be deleted");
            }
        }catch(Exception e){
            System.out.println("Error in Deleting on checkout");
        }
    }

    public void Delete_checkoutUser(int UId){
        boolean check = false;
        try{
            connect = tryConnect();
            statement = connect.prepareStatement("SELECT * FROM checkout");
            set = statement.executeQuery();
            while(set.next()){
                int id = set.getInt("user_id");
                if(id == UId){
                    check= true;
                }
            }
            statement.close();
            if(check){
                statement = connect.prepareStatement("DELETE FROM checkout where user_id = UId");
                statement.executeUpdate();
                statement.close();
                connect.close();
            }else{
                System.out.println("User ID wasn't found in checkout to be deleted");
            }
        }catch(Exception e){
            System.out.println("Error in Deleting on checkout");
        }
    }


    public void Update_checkout(int Id, int bookID, int userId){
        boolean check = false;
        try{
            connect = tryConnect();
            statement = connect.prepareStatement("SELECT * FROM checkout");
            set = statement.executeQuery();
            while(set.next()){
                int id = set.getInt("id");
                if(id == Id){
                    check= true;
                }
            }
            statement.close();
            if(check){
                statement = connect.prepareStatement("UPDATE checkout SET book_id = ? and user_id = ? where id = ?");
                statement.setInt(1, bookID);
                statement.setInt(2, userId);
                statement.setInt(3, Id);
                statement.executeUpdate();
                statement.close();
                connect.close();
            }else{
                System.out.println("ID wasn't found in checkout for Update");
            }
        }catch(Exception e){
            System.out.println("Error in Updating on checkout");
        }
    }

}
