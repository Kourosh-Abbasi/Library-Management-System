package org.example;

import java.io.Serializable;

public class User implements Serializable {
    String userName;
    String phoneNumber;
    int userId;
    String password;
    String registerDate;

    public User(String userName, String phoneNumber, int userId, String registerDate, String password) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.password = password;
        this.registerDate = registerDate;
    }
}
