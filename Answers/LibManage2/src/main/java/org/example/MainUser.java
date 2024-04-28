package org.example;
public class MainUser
{
    private String Name,ID,PhoneNumber,RegisterDate;

    public MainUser(String name, String ID, String phoneNumber, String registerDate) {
        Name = name;
        this.ID = ID;
        PhoneNumber = phoneNumber;
        RegisterDate = registerDate;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getRegisterDate() {
        return RegisterDate;
    }

    public void setRegisterDate(String registerDate) {
        RegisterDate = registerDate;
    }


}
