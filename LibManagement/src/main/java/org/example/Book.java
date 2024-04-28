package org.example;

import java.io.Serializable;

public class Book implements Serializable {
    String bookName;
    String bookAuthor;
    int bookId;
    boolean isAvailable;

    public Book(String bookName, String bookAuthor, int bookId, boolean isAvailable) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookId = bookId;
        this.isAvailable = isAvailable;
    }

}
