package org.example;

public class Admin extends MainUser{
    String Password;
    public Admin(String name, String ID, String phoneNumber, String registerDate, String password) {
        super(name, ID, phoneNumber, registerDate);
        Password = password;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }
}
