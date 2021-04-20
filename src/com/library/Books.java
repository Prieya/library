package com.library;
import java.sql.*;

public class Books {
    private Connection conn;
    private PreparedStatement statement;
    private ResultSet result;


    public Connection Tryconnection(){
        final String url = "jdbc:postgresql://localhost:5432/library";
        try{
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to book is success");
        }catch(SQLException e){
            System.out.println("Connection error" + e);
        }
        return conn;
    }

    public void addBook(String title, String isbn, String description, int pages, int chapters, int author_id){
        if(!checkBook(isbn)) {
            System.out.println("The information enter is wrong" +
                    "Check again with 8 number as the ID");
        }
        try{
            conn = Tryconnection();
            statement = conn.prepareStatement("INSERT INTO book(title, isbn, description, pages, chapters, author_id)" +
                     "VALUES (?, ?, ?, ?)");
            statement.setString(1, title);
            statement.setString(2, isbn);
            statement.setString(3, description);
            statement.setInt(4, pages);
            statement.setInt(5, chapters);
            statement.setInt(6, author_id);
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
    public void deleteBook(String id, String title){
        try{
            if(BookExist(id, title)) {
                conn = Tryconnection();
                statement = conn.prepareStatement("DELETE FROM book WHERE idbn = ? AND title = ?");
                statement.setString(1, id);
                statement.setString(2, title);

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

    public boolean BookExist(String idbn, String title){
        boolean UserExist = false;
        try{
            conn = Tryconnection();
            statement = conn.prepareStatement("SELECT * FROM book ");
            result = statement.executeQuery();
            while(result.next()){
                String Bookid = result.getString("idbn");
                String BookTitle = result.getString("title");
                if(idbn.equals(Bookid) && title.equalsIgnoreCase(BookTitle)){
                    UserExist = true;
                }
            }
        }catch(SQLException e){
            System.out.println("Error finding Book");
        }
        return UserExist;
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
            conn = Tryconnection();
            statement = conn.prepareStatement("SELECT * FROM book ");
            result = statement.executeQuery();
            while(result.next()){
                 Bookidbn = result.getString("idbn");
                 BookTitle = result.getString("title");
                if(Bookidbn.equalsIgnoreCase(isbn) && BookTitle.equalsIgnoreCase(title)){
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
            conn = Tryconnection();
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
