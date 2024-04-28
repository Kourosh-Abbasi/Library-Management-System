package org.example;
/*
rentID
rentDate
userID
bookID
 */
public class Rent {
    String RentID,RentDate;
    Book Book;
    User User;
    public Rent(String rentID, String rentDate, Book book, User user) {
        RentID = rentID;
        RentDate = rentDate;
        Book = book;
        User = user;
    }
}
