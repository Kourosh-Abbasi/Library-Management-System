package org.example;

public interface LibraryMethods {
    public void Welcome();
    public void AddNewBook(String name,String title,String author);
    public void AddNewUser(String UserName, String PhoneNumber);
    public void AddNewAdmin(String UserName, String PhoneNumber,String Password);
    public void RemoveBook(String id);
    public void RemoveUser(String userID);
    public void RemoveAdmin(String adminID);
    public void ShowBookList();
    public void ShowUserList();
    public void ShowAdminList();  
    public void RentBook(String userID, String bookID);
    public void ReturnBook(String userID, String bookID);
    public String[] GetRepo(String FilePath);  
    public boolean TestID(String ID, String FilePath);  
    public User GetUser(String userID);  
    public Book GetBook(String BookID);  
    public void ShowOptions ()throws Exception;
    public String SearchRentID(String userID,String bookID);
    public boolean CheckCommand(String command);
    public void RunByCommand();
    public String FindAdminPassByID(String adminID);
    public void ShowRentList();
}
