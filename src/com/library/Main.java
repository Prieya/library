package com.library;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	Books book = new Books();
	Author author = new Author();
	genre g = new genre();
	book_genre genre = new book_genre();
	generalDeleteBook(book);
    }

    public static void General_book(Books book, Author author, genre g, book_genre b){
        Scanner k = new Scanner(System.in);
        System.out.println("Enter The title of the Book.");
        String bookTitle = k.nextLine();
        System.out.println("Enter the ISBN of the Book");
        String bookISBN= k.nextLine();
        System.out.println("Enter a brief discription of the Book, if none enter 'N/A'");
        String bookdiscription= k.nextLine();
        System.out.println("Enter the author name of the Book");
        String bookauthor = k.nextLine();
        System.out.println("Enter the book genre.");
        String bookGenre= k.nextLine();
        System.out.println("Enter the total amount of page in the Book");
        int bookpages= k.nextInt();

        author = new Author();
        author.addAuthor(bookauthor);

        g = new genre();
        g.AddGenre(bookGenre);

        book = new Books();
        book.addBook(bookTitle, bookISBN, bookdiscription, bookpages, author.getauthorByID(bookauthor));

        b = new book_genre();
        b.Book_Genre(book.getBookID(bookTitle), g.getGenrebyID(bookGenre));
    }

    public static void generalCheckout(Books book, users u, checkout c){
        Scanner k = new Scanner(System.in);
        System.out.println("Enter The title of the Book");
        String bookTitle = k.nextLine();
        System.out.println("Enter the ISBN of the Book");
        String bookISBN= k.nextLine();
        System.out.println("Enter the user name");
        String Username = k.nextLine();
        System.out.println("Enter the user email");
        String useremail = k.nextLine();
        System.out.println("Enter the user password");
        String userpassword = k.nextLine();

        book = new Books();
        u = new users();
        u.user_adding(useremail, Username, userpassword);
        c = new checkout();
        c.adding_checkout(book.getBookID(bookTitle), u.GetUserByID(Username));
    }

    public static void MembergeneralCheckout(Books book, users u, checkout c){
        Scanner k = new Scanner(System.in);
        System.out.println("Enter The title of the Book");
        String bookTitle = k.nextLine();
        System.out.println("Enter the ISBN of the Book");
        String bookISBN= k.nextLine();
        System.out.println("Enter the user name");
        String Username = k.nextLine();
        System.out.println("Enter the user password");
        String userpassword = k.nextLine();

        book = new Books();
        u = new users();
        c = new checkout();
        c.adding_checkout(book.getBookID(bookTitle), u.GetUserByID(Username));
    }

    public static void generalDeleteBook(Books book){
        Scanner k = new Scanner(System.in);
        System.out.println("Enter The title of the Book that need to be delete");
        String bookTitle = k.nextLine();
        System.out.println("Enter the ISBN of the Book that need to be delete");
        String bookISBN= k.nextLine();

        int bookId = book.getBookID(bookTitle);
        book.deleteBook(bookId);
        book_genre b = new book_genre();
        checkout check = new checkout();
        b.DeleteBookGenre(bookId);
        check.Delete_checkoutBook(bookId);
    }
}
