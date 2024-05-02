package org.example;

import java.io.Serializable;

public class Rent implements Serializable {
    int rentId;
    String rentDate;
    User user;
    Book book;

    public Rent(int rentId, String rentDate, User user, Book book) {
        this.rentId = rentId;
        this.rentDate = rentDate;
        this.user = user;
        this.book = book;
    }

}
