package com.library;
import com.library.Helper_Method.DoesExist;

import java.sql.*;

public class Books {
    private DoesExist e = new DoesExist();
    private TryConnect b = new TryConnect();
    private Connection conn;
    private PreparedStatement statement;
    private ResultSet result;


    public void addBook(String title, String isbn, String description, int pages, int author_id){
        if(!checkBook(isbn)) {
            System.out.println("The information enter is wrong" +
                    "Check again with 8 number as the ID");
        }
        try{
            conn = b.Tryconnection();
            statement = conn.prepareStatement("INSERT INTO book(title, isbn, description, pages, author_id)" +
                     "VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, title);
            statement.setString(2, isbn);
            statement.setString(3, description);
            statement.setInt(4, pages);
            statement.setInt(5, author_id);
            statement.executeUpdate();
            statement.close();
            conn.close();
        }catch (SQLException e){
            System.out.println("Error adding to Book" + e);
        }
    }



    /*    public void updateBook(String id, String Updatecheckout){
            try{
                conn = Tryconnection();
                statement = conn.prepareStatement("UPDATE books SET loaned = ? where idbn = ?");
                statement.setString(1, Updatecheckout);
                statement.setString(2, id);
                statement.close();
                conn.close();
            }catch(SQLException e){
                System.out.println("Error updating Book " + e);
            }

        }*/
    public void deleteBook(int id){
        try{
            if(e.Exist("book", id)) {
                conn = b.Tryconnection();
                statement = conn.prepareStatement("DELETE FROM book WHERE id = ?");
                statement.setInt(1, id);
                statement.executeUpdate();
                statement.close();
                conn.close();
            }else{
                System.out.println("Cannot delete a Book that does not Exist");
            }
        }catch (SQLException e){
            System.out.println("Error deleting to Book" + e);
        }
    }


    public boolean checkBook(String id){
        boolean correction = true;
        for(int i = 0; i < id.length(); i++){
            if(!(id.charAt(i) >= '0' && id.charAt(i) <= '9')){
                return false;
            }
        }

        return correction;
    }

    public int getBookID(String title, String isbn){
        String Bookidbn = "";
        String BookTitle = "";
        int BookId = 0;
        try{
            conn = b.Tryconnection();
            statement = conn.prepareStatement("SELECT * FROM book ");
            result = statement.executeQuery();
            while(result.next()){
                 BookTitle = result.getString("title");
                Bookidbn = result.getString("isbn");
                if(Bookidbn.equals(isbn) && BookTitle.equalsIgnoreCase(title)){
                    BookId = result.getInt("id");
                }
            }
        }catch(SQLException e){
            System.out.println("Error getting book Id");
        }
        return BookId;
    }

    public int getBookAuthor(String title, String isbn){
        String Bookidbn = "";
        String BookTitle = "";
        int BookAuthorId = 0;
        try{
            conn = b.Tryconnection();
            statement = conn.prepareStatement("SELECT * FROM book ");
            result = statement.executeQuery();
            while(result.next()){
                Bookidbn = result.getString("idbn");
                BookTitle = result.getString("title");
                if(Bookidbn.equalsIgnoreCase(isbn) && BookTitle.equalsIgnoreCase(title)){
                    BookAuthorId = result.getInt("id");
                }
            }
        }catch(SQLException e){
            System.out.println("Error getting book Author Id");
        }
        return BookAuthorId;
    }
}
