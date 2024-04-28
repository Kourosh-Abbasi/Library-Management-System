package org.example;

import java.io.*;
import java.util.*;

public class Library implements LibraryMethods {
    ////////////////////////////////////////////////////////////////////////////////////////
    String OperationHours;
    String LibraryName;
    String AllPermissionPassword;
    int Capacity;
    String[] BookRepo = GetRepo("BookRepo.txt");
    String[] UserRepo = GetRepo("UserRepo.txt");
    String[] AdminRepo = GetRepo("AdminRepo.txt");
    String[] RentRepo = GetRepo("RentRepo.txt");
    public Library(String OperationHours, String LibraryName, String AllPermissionPassword, int Capacity){
        this.OperationHours = OperationHours;
        this.LibraryName = LibraryName;
        this.Capacity = Capacity;
        this.AllPermissionPassword = AllPermissionPassword;
    }
    ////////////////////////////////////////////////////////////////////////////////////////
    //Methods :
    @Override
    public void Welcome() {
        System.out.println(" Welcome to " + LibraryName);
        System.out.println(" Operation hours : " + OperationHours);
    }
    @Override
    public void AddNewBook(String name, String title, String author) {
        Scanner input = new Scanner(System.in);
        System.out.print(" admin ID : ");
        String adminID = input.nextLine();
        if (!TestID(adminID, "AdminRepo.txt")) {
            System.out.println(" admin not found");
            return;
        }
        System.out.print(" admin Password : ");
        String adminPassword = input.nextLine();
        if (!adminPassword.equals(FindAdminPassByID(adminID))) {
            System.out.println(" Password is incorrect");
            return;
        }
        System.out.println();
        int BookID = 1;
        boolean IsAvailable = true;
        Random rnd = new Random();
        while (TestID(String.valueOf(BookID), "BookRepo.txt")) {
            BookID = rnd.nextInt(Capacity) + 1;
        }
        try {
            FileWriter fw = new FileWriter("BookRepo.txt", true);
            fw.write(BookID + "\n" + name + "\n" + title + "\n" + title + "\n" + IsAvailable + "\n");
            fw.close();
            System.out.println(" Book added successfully");
        } catch (IOException e) {
        }
    }

    @Override
    public void RemoveBook(String id) {
        if (!TestID(id, "BookRepo.txt")) {
            System.out.println(" Book not found!!");
            return;
        }
        Scanner input = new Scanner(System.in);
        System.out.print(" admin ID : ");
        String adminID = input.nextLine();
        if (!TestID(adminID, "AdminRepo.txt")) {
            System.out.println(" admin not found");
            return;
        }
        System.out.print(" admin Password : ");
        String adminPassword = input.nextLine();
        if (!adminPassword.equals(FindAdminPassByID(adminID))) {
            System.out.println(" Password is incorrect");
            return;
        }
        try {
            FileWriter fw = new FileWriter("BookRepo.txt");
            for (int i = 0; i < BookRepo.length; i++) {
                if (BookRepo[i].equals(id)) {
                    i = i + 5;
                }
                if (i >= BookRepo.length) {
                    break;
                }
                fw.write(BookRepo[i] + "\n");
            }
            fw.close();
            System.out.println(" Book removed successfully");
        } catch (IOException e) {
        }
    }

    @Override
    public void ShowBookList() {
        BookRepo = GetRepo("BookRepo.txt");
        System.out.println("---------------------------------------------");
        int k = 1;
        for (int i = 0; i < BookRepo.length; i = i + 5) {
            System.out.println(" Book[" + k + "]");
            System.out.println(" bookID     : " + BookRepo[i]);
            System.out.println(" bookName   : " + BookRepo[i + 1]);
            System.out.println(" bookTitle  : " + BookRepo[i + 2]);
            System.out.println(" bookAuthor : " + BookRepo[i + 3]);
            System.out.println(" Available? : " + BookRepo[i + 4]);
            System.out.println("---------------------------------------------");
            k++;
        }
    }

    @Override
    public void AddNewUser(String UserName, String PhoneNumber) {
        Scanner input = new Scanner(System.in);
        System.out.print(" admin ID : ");
        String adminID = input.nextLine();
        if (!TestID(adminID, "AdminRepo.txt")) {
            System.out.println(" admin not found");
            return;
        }
        System.out.print(" admin Password : ");
        String adminPassword = input.nextLine();
        if (!adminPassword.equals(FindAdminPassByID(adminID))) {
            System.out.println(" Password is incorrect");
            return;
        }
        UserRepo = GetRepo("UserRepo.txt");
        String RegisterDate, UserID;
        Date d = new Date();
        RegisterDate = d.toString();
        int id = 1;
        Random rnd = new Random();
        while (TestID(String.valueOf(id), "UserRepo.txt")) {
            id = rnd.nextInt(100000);
        }
        UserID = String.valueOf(id);
        try {
            FileWriter fw = new FileWriter("UserRepo.txt", true);
            fw.write(UserID + "\n" + UserName + "\n" + PhoneNumber + "\n" + RegisterDate + "\n");
            fw.close();
            System.out.println(" User added successfully");
        } catch (IOException e) {
        }
    }

    @Override
    public void RemoveUser(String userID) {
        Scanner input = new Scanner(System.in);
        System.out.print(" admin ID : ");
        String adminID = input.nextLine();
        if (!TestID(adminID, "AdminRepo.txt")) {
            System.out.println(" admin not found");
            return;
        }
        System.out.print(" admin Password : ");
        String adminPassword = input.nextLine();
        if (!adminPassword.equals(FindAdminPassByID(adminID))) {
            System.out.println(" Password is incorrect");
            return;
        }
        try {
            FileWriter fw = new FileWriter("UserRepo.txt");
            for (int i = 0; i < UserRepo.length; i++) {
                if (UserRepo[i].equals(userID)) {
                    i = i + 4;
                }
                if (i >= UserRepo.length) {
                    break;
                }
                fw.write(UserRepo[i] + "\n");
            }
            fw.close();
            System.out.println(" User removed successfully");
        } catch (IOException e) {
        }
    }

    @Override
    public void ShowUserList() {
        Scanner input = new Scanner(System.in);
        System.out.print(" admin ID : ");
        String adminID = input.nextLine();
        if (!TestID(adminID, "AdminRepo.txt")) {
            System.out.println(" admin not found");
            return;
        }
        System.out.print(" admin Password : ");
        String adminPassword = input.nextLine();
        if (!adminPassword.equals(FindAdminPassByID(adminID))) {
            System.out.println(" Password is incorrect");
            return;
        }
        UserRepo = GetRepo("UserRepo.txt");
        System.out.println("---------------------------------------------");
        int k = 1;
        for (int i = 0; i < UserRepo.length; i = i + 4) {
            System.out.println(" User[" + k + "]");
            System.out.println(" userID           : " + UserRepo[i]);
            System.out.println(" userName         : " + UserRepo[i + 1]);
            System.out.println(" userPhoneNumber  : " + UserRepo[i + 2]);
            System.out.println(" userRegisterDate : " + UserRepo[i + 3]);
            System.out.println("---------------------------------------------");
            k++;
        }
    }

    @Override
    public void AddNewAdmin(String UserName, String PhoneNumber, String Password) {
        Scanner input = new Scanner(System.in);
        try {
            FileReader fr = new FileReader("Library Info.txt");
            Scanner sc = new Scanner(fr);
            AllPermissionPassword = sc.nextLine();
        } catch (IOException e) {
        }
        System.out.print(" all permission password : ");
        String x = input.nextLine();
        if (!x.equals(AllPermissionPassword)) {
            System.out.println(" Password is incorrect");
            return;
        }
        AdminRepo = GetRepo("AdminRepo.txt");
        String RegisterDate, UserID;
        Date d = new Date();
        RegisterDate = d.toString();
        int id = 1;
        Random rnd = new Random();
        while (TestID(String.valueOf(id), "AdminRepo.txt")) {
            id = rnd.nextInt(100000);
        }
        UserID = String.valueOf(id);
        try {
            FileWriter fw = new FileWriter("AdminRepo.txt", true);
            fw.write(UserID + "\n" + UserName + "\n" + PhoneNumber + "\n" + Password + "\n" + RegisterDate + "\n");
            fw.close();
            System.out.println(" Admin added successfully");
            System.out.println(" admin ID       : "+id);
            System.out.println(" admin password : "+Password);
        } catch (IOException e) {
        }
    }

    @Override
    public void RemoveAdmin(String adminID) {
        Scanner input = new Scanner(System.in);
        try {
            FileReader fr = new FileReader("Library Info.txt");
            Scanner sc = new Scanner(fr);
            AllPermissionPassword = sc.nextLine();
        } catch (IOException e) {
        }
        System.out.print(" all permission password : ");
        String x = input.nextLine();
        if (!x.equals(AllPermissionPassword)) {
            System.out.println(" Password is incorrect");
            return;
        }
        if (!TestID(adminID, "AdminRepo.txt")) {
            System.out.println(" Admin not found");
            return;
        }
        try {
            FileWriter fw = new FileWriter("AdminRepo.txt");
            for (int i = 0; i < UserRepo.length; i++) {
                if (AdminRepo[i].equals(adminID)) {
                    i = i + 5;
                }
                if (i >= AdminRepo.length) {
                    break;
                }
                fw.write(AdminRepo[i] + "\n");
            }
            fw.close();
            System.out.println(" Admin removed successfully");
        } catch (IOException e) {
        }
    }
    @Override
    public void ShowAdminList() {
        Scanner input = new Scanner(System.in);
        try {
            FileReader fr = new FileReader("Library Info.txt");
            Scanner sc = new Scanner(fr);
            AllPermissionPassword = sc.nextLine();
        } catch (IOException e) {

        }
        System.out.print(" all permission password : ");
        String x = input.nextLine();
        if (!x.equals(AllPermissionPassword)) {
            System.out.println(" Password is incorrect");
            return;
        }
        AdminRepo = GetRepo("AdminRepo.txt");
        System.out.println("---------------------------------------------");
        int k = 1;
        for (int i = 0; i < AdminRepo.length; i = i + 5) {
            System.out.println(" Admin[" + k + "]");
            System.out.println(" adminID           : " + AdminRepo[i]);
            System.out.println(" adminName         : " + AdminRepo[i + 1]);
            System.out.println(" adminPhoneNumber  : " + AdminRepo[i + 2]);
            System.out.println(" adminPassword     : " + AdminRepo[i + 3]);
            System.out.println(" adminRegisterDate : " + AdminRepo[i + 4]);
            System.out.println("---------------------------------------------");
            k++;
        }
    }

    @Override
    public void RentBook(String userID, String bookID) {
        if (!TestID(userID, "UserRepo.txt")) {
            System.out.println(" User not found");
            return;
        }
        if (!TestID(bookID, "BookRepo.txt")) {
            System.out.println(" Book not found");
            return;
        }
        User user = GetUser(userID);
        Book book = GetBook(bookID);
        int rentID = 1;
        Random rnd = new Random();
        while (TestID(String.valueOf(rentID), "RentRepo.txt")) {
            rentID = rnd.nextInt(Capacity) + 1;
        }
        Date d = new Date();
        String rentDate = d.toString();
        Rent rent = new Rent(String.valueOf(rentID), rentDate, book, user);
        try {
            FileWriter fw = new FileWriter("RentRepo.txt", true);
            fw.write(rentID + "\n" + rentDate + "\n" + rent.User.getID() + "\n" + rent.Book.getBookId() + "\n");
            fw.close();
        } catch (IOException e) {

        }
        for (int i = 0; i < BookRepo.length; i++) {
            if (BookRepo[i].equals(String.valueOf(rent.Book.getBookId()))) {
                if (BookRepo[i + 4].equals("false")) {
                    System.out.println(" Book is already rented!!");
                    return;
                }
                BookRepo[i + 4] = "false";
                break;
            }
        }
        try {
            FileWriter fw2 = new FileWriter("BookRepo.txt");
            for (int i = 0; i<BookRepo.length; i++) {
                fw2.write(BookRepo[i] + "\n");
            }
            fw2.close();
            System.out.println(" Book rented successfully");
        } catch (IOException e) {

        }
    }

    @Override
    public void  ReturnBook(String userID, String bookID) {
        if (!TestID(userID, "UserRepo.txt")) {
            System.out.println(" User not found");
            return;
        }
        if (!TestID(bookID, "BookRepo.txt")) {
            System.out.println(" Book not found");
            return;
        }
        String rentID;
        rentID = SearchRentID(userID, bookID);
        BookRepo = GetRepo("BookRepo.txt");
        for (int i = 0; i < BookRepo.length; i++) {
            if(BookRepo[i].equals(bookID)){
                BookRepo[i+4]="true";
            }
        }
        try{
            FileWriter fw = new FileWriter("BookRepo.txt");
            for (int i = 0; i < BookRepo.length; i++) {
                fw.write(BookRepo[i]+"\n");
            }
            fw.close();
        }catch (Exception e){}
        RentRepo = GetRepo("RentRepo.txt");
        try {
            FileWriter fw = new FileWriter("RentRepo.txt");
            for (int i = 0; i < RentRepo.length; i++) {
                if(RentRepo[i].equals(rentID)){
                     i = i+4;
                }
                fw.write(RentRepo[i]+"\n");
            }
            fw.close();
        }catch (Exception e){}
        System.out.println(" Book returned successfully");
    }

    @Override
    public String[] GetRepo(String FilePath) {
        try {
            FileReader fr = new FileReader(FilePath);
            Scanner sc = new Scanner(fr);
            ArrayList<String> b = new ArrayList<>();
            while (sc.hasNextLine()) {
                b.add(sc.nextLine());
            }
            Object[] b1 = b.toArray();
            String[] b2 = new String[b1.length];
            for (int i = 0; i < b1.length; i++) {
                b2[i] = (String) b1[i];
            }
            return b2;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public boolean TestID(String ID, String FilePath) {
        ArrayList<String> id = new ArrayList<>();
        try {
            FileReader fr = new FileReader(FilePath);
            Scanner scan = new Scanner(fr);
            while (scan.hasNextLine()) {
                id.add(scan.nextLine());
            }
            Object[] id2 = id.toArray();
            boolean stat = false;
            for (Object o : id2) {
                if (ID.equals(o)) {
                    stat = true;
                    break;
                }
            }
            return stat;
        } catch (IOException e) {

            return true;
        }
    }

    @Override
    public User GetUser(String userID) {
        String[] users = GetRepo("UserRepo.txt");
        int k = -1;
        for (int i = 0; i < users.length; i++) {
            if (users[i].equals(userID)) {
                k = i;
            }
        }
        if (k == -1) {
            return null;
        }
        return new User(users[k + 1], users[k], users[k + 2], users[k + 3]);
    }

    @Override
    public Book GetBook(String BookID) {
        String[] b = GetRepo("BookRepo.txt");
        int k = -1;
        for (int i = 0; i < b.length; i++) {
            if (b[i].equals(BookID)) {
                k = i;
            }
        }
        if (k == -1) {
            return null;
        }
        return new Book(b[k + 1], b[k + 2], b[k + 3], Integer.parseInt(b[k]), Boolean.parseBoolean(b[k + 4]));
    }

    public void ShowRentList() {
        Scanner input = new Scanner(System.in);
        System.out.print(" admin ID : ");
        String adminID = input.nextLine();
        if (!TestID(adminID, "AdminRepo.txt")) {
            System.out.println(" admin not found");
            return;
        }
        System.out.print(" admin Password : ");
        String adminPassword = input.nextLine();
        if (!adminPassword.equals(FindAdminPassByID(adminID))) {
            System.out.println(" Password is incorrect");
            return;
        }
        RentRepo = GetRepo("RentRepo.txt");
        System.out.println("---------------------------------------------");
        int k = 1;
        for (int i = 0; i < RentRepo.length; i = i + 4) {
            System.out.println(" Rent[" + k + "]");
            System.out.println(" rentID   : " + RentRepo[i]);
            System.out.println(" rentDate : " + RentRepo[i + 1]);
            System.out.println(" userID   : " + RentRepo[i + 2]);
            System.out.println(" bookID   : " + RentRepo[i + 3]);
            System.out.println("---------------------------------------------");
            k++;
        }
    }

    @Override
    public String SearchRentID(String userID, String bookID) {
        RentRepo = GetRepo("RentRepo.txt");
        for (int i = 0; i < RentRepo.length; i++) {
            if (RentRepo[i].equals(userID) && RentRepo[i + 1].equals(bookID)) {
                return RentRepo[i - 2];
            }
        }
        return null;
    }

    @Override
    public String FindAdminPassByID(String adminID) {
        AdminRepo = GetRepo("AdminRepo.txt");
        for (int i = 0; i < AdminRepo.length; i++) {
            if (AdminRepo[i].equals(adminID)) {
                return AdminRepo[i + 3];
            }
        }
        return null;
    }

    @Override
    public void ShowOptions() {
        System.out.println(" -----------------------------------------------------------");
        System.out.println(" Add options : ");
        System.out.println(" lib::add::book::<bookName>::<bookTitle>::<bookAuthor>");//admin pass needed
        System.out.println(" lib::add::user::<userName>::<phoneNumber>");//admin pass needed
        System.out.println(" lib::add::admin::<adminName>::<phoneNumber>::<password>");//all permission pass needed
        System.out.println(" -----------------------------------------------------------");
        System.out.println(" Remove options : ");
        System.out.println(" lib::remove::book::<bookID>");//admin pass needed
        System.out.println(" lib::remove::user::<userID>");//admin pass needed
        System.out.println(" lib::remove::admin::<adminID>");//all permission pass needed
        System.out.println(" -----------------------------------------------------------");
        System.out.println(" Show lists : ");
        System.out.println(" lib::show::bookList");//no pass needed
        System.out.println(" lib::show::userList");//admin pass needed
        System.out.println(" lib::show::adminList");//all permission pass needed
        System.out.println(" lib::show::rentList");//all permission pass needed
        System.out.println(" -----------------------------------------------------------");
        System.out.println(" Rent & Return : ");
        System.out.println(" lib::rent::book::<userID>::<bookID> ");//no pass needed
        System.out.println(" lib::return::book::<userID>::<bookID>");//no pass needed
        System.out.println(" -----------------------------------------------------------");
    }

    @Override
    public boolean CheckCommand(String command) {
        command = command.toLowerCase();
        if (command.contains("help")) {
            return true;
        }
        if (command.contains("exit")) {
            return true;
        }
        String[] c2 = command.split("::");
        if (c2.length < 3) {
            return false;
        }
        String c3 = c3 = c2[0] + "::" + c2[1] + "::" + c2[2];
        boolean x1 = c3.equals("lib::add::book") || c3.equals("lib::remove::book") || c3.equals("lib::rent::book") ||
                c3.equals("lib::return::book") || c3.equals("lib::show::booklist") || c3.equals("lib::add::user") ||
                c3.equals("lib::remove::user") || c3.equals("lib::show::userlist") || c3.equals("lib::add::admin") ||
                c3.equals("lib::remove::admin") || c3.equals("lib::show::adminlist") || c3.equals("lib::show::rentlist");
        if (!x1) {
            return false;
        }
        if (c3.equals("lib::add::book") && c2.length != 6) {
            return false;
        }
        if (c3.equals("lib::remove::book") && c2.length != 4) {
            return false;
        }
        if (c3.equals("lib::rent::book") && c2.length != 5) {
            return false;
        }
        if (c3.equals("lib::return::book") && c2.length != 5) {
            return false;
        }
        if (c3.equals("lib::show::booklist") && c2.length != 3) {
            return false;
        }
        if (c3.equals("lib::add::user") && c2.length != 5) {
            return false;
        }
        if (c3.equals("lib::remove::user") && c2.length != 4) {
            return false;
        }
        if (c3.equals("lib::show::userlist") && c2.length != 3) {
            return false;
        }
        if (c3.equals("lib::add::admin") && c2.length != 6) {
            return false;
        }
        if (c3.equals("lib::remove::admin") && c2.length != 4) {
            return false;
        }
        if (c3.equals("lib::show::adminlist") && c2.length != 3) {
            return false;
        }
        if (c3.equals("lib::show::rentlist") && c2.length != 3) {
            return false;
        }
        return true;
    }
    @Override
    public void RunByCommand() {
        System.out.println(" Welcome to " + LibraryName);
        System.out.println(" Operation hours : " + OperationHours);
        for (; ; ) {
            Scanner input = new Scanner(System.in);
            String command;
            System.out.println(" Enter a command and press [Enter] : ");
            for (; ; ) {
                System.out.print(" >>> ");
                command = input.nextLine();
                if (CheckCommand(command)) {
                    break;
                }
                System.out.println(" Command not found!!");
            }
            command = command.toLowerCase();
            if (command.contains("help")) {
                ShowOptions();
                continue;
            }
            if (command.contains("exit")) {
                return;
            }
            String[] c2 = command.split("::");
            String c3 = c2[0] + "::" + c2[1] + "::" + c2[2];
            switch (c3) {
                case "lib::add::book": {
                    AddNewBook(c2[3], c2[4], c2[5]);
                    break;
                }
                case "lib::remove::book": {
                    RemoveBook(c2[3]);
                    break;
                }
                case "lib::rent::book": {
                    RentBook(c2[3], c2[4]);
                    break;
                }
                case "lib::return::book": {
                    ReturnBook(c2[3], c2[4]);
                    break;
                }
                case "lib::show::booklist": {
                    ShowBookList();
                    break;
                }
                case "lib::add::user": {
                    AddNewUser(c2[3], c2[4]);
                    break;
                }
                case "lib::remove::user": {
                    RemoveUser(c2[3]);
                    break;
                }
                case "lib::show::userlist": {
                    ShowUserList();
                    break;
                }
                case "lib::add::admin": {
                    AddNewAdmin(c2[3], c2[4], c2[5]);
                    break;
                }
                case "lib::remove::admin": {
                    RemoveAdmin(c2[3]);
                    break;
                }
                case "lib::show::adminlist": {
                    ShowAdminList();
                    break;
                }
                case "lib::show::rentlist": {
                    ShowRentList();
                    break;
                }
                default: {
                    ShowOptions();
                }
            }
        }
    }
}