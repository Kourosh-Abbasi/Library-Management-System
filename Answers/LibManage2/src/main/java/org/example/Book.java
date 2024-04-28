package org.example;

public class Book{
    String BookName, BookTitle, BookAuthor;
    int BookId;
    boolean IsAvailable;

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getBookTitle() {
        return BookTitle;
    }

    public void setBookTitle(String bookTitle) {
        BookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return BookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        BookAuthor = bookAuthor;
    }

    public int getBookId() {
        return BookId;
    }

    public void setBookId(int bookId) {
        BookId = bookId;
    }

    public boolean getisAvailable() {
        return IsAvailable;
    }

    public void setAvailable(boolean available) {
        IsAvailable = available;
    }

    public Book(String bookName, String bookTitle, String bookAuthor, int bookId, boolean isAvailable) {
        BookName = bookName;
        BookTitle = bookTitle;
        BookAuthor = bookAuthor;
        BookId = bookId;
        IsAvailable = isAvailable;
    }
}
