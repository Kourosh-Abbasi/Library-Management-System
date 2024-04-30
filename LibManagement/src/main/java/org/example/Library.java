////////////////////////////******************/////////////////////////////////
////////////////////////////**Kourosh Abbasi**/////////////////////////////////
////////////////////////////******************/////////////////////////////////
package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Library {
    String libraryName;
    String operationHours;
    String libraryPassword;
    ArrayList<Book> bookRepo = new ArrayList<>();
    ArrayList<User> userRepo = new ArrayList<>();
    ArrayList<Rent> rentRepo = new ArrayList<>();
    int booksId = 1;
    int usersId = 1;
    int rentsId = 1;

    public Library(String libraryName, String operationHours, String libraryPassword) {
        this.libraryName = libraryName;
        this.operationHours = operationHours;
        this.libraryPassword = libraryPassword;
        CheckFiles();
        RepoUpdater();
        if (!this.bookRepo.isEmpty()) {
            booksId = this.bookRepo.getLast().bookId + 1;
        }
        if (!this.userRepo.isEmpty()) {
            usersId = this.userRepo.getLast().userId + 1;
        }
        if (!this.rentRepo.isEmpty()) {
            rentsId = this.rentRepo.getLast().rentId + 1;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////
    //Methods :
    public void Welcome() {
        System.out.println("*** Welcome to " + this.libraryName + " ***");
        System.out.println("#Operation hours : " + this.operationHours);
        System.out.println("#For see options write <help>");
        System.out.println("#For exit write <exit>");
    }

    ///////////////////////////////////////////////////////////////////////////////

    public void CheckFiles() {
        try {
            File f1 = new File("Books.txt");
            File f2 = new File("Users.txt");
            File f3 = new File("Rents.txt");
            boolean a = f1.createNewFile();
            boolean b = f2.createNewFile();
            boolean c = f3.createNewFile();
        } catch (Exception ignored) {
        }
    }

    ///////////////////////////////////////////////////////////////////////////////
    public void RepoSaver() {
        try {
            FileOutputStream fout1 = new FileOutputStream("Books.txt");
            ObjectOutputStream oout1 = new ObjectOutputStream(fout1);
            oout1.writeObject(this.bookRepo);
            fout1.close();
            oout1.close();
            FileOutputStream fout2 = new FileOutputStream("Users.txt");
            ObjectOutputStream oout2 = new ObjectOutputStream(fout2);
            oout2.writeObject(this.userRepo);
            fout2.close();
            oout2.close();
            FileOutputStream fout3 = new FileOutputStream("Rents.txt");
            ObjectOutputStream oout3 = new ObjectOutputStream(fout3);
            oout3.writeObject(this.rentRepo);
            fout3.close();
            oout3.close();
        } catch (Exception ignored) {
        }
    }

    ///////////////////////////////////////////////////////////////////////////////
    public void RepoUpdater() {
        try {
            FileInputStream fin1 = new FileInputStream("Books.txt");
            ObjectInputStream oin1 = new ObjectInputStream(fin1);
            this.bookRepo = (ArrayList<Book>) oin1.readObject();
            fin1.close();
            oin1.close();
            FileInputStream fin2 = new FileInputStream("Users.txt");
            ObjectInputStream oin2 = new ObjectInputStream(fin2);
            this.userRepo = (ArrayList<User>) oin2.readObject();
            fin2.close();
            oin2.close();
            FileInputStream fin3 = new FileInputStream("Rents.txt");
            ObjectInputStream oin3 = new ObjectInputStream(fin3);
            this.rentRepo = (ArrayList<Rent>) oin3.readObject();
            fin3.close();
            oin3.close();
        } catch (Exception ignored) {
        }
    }

    ///////////////////////////////////////////////////////////////////////////////
    public boolean TestPassword() {
        Scanner input = new Scanner(System.in);
        System.out.print("Library password required : ");
        String pass = input.nextLine();
        return libraryPassword.equals(pass);
    }

    ///////////////////////////////////////////////////////////////////////////////
    public boolean TestUserId(int userId) {
        boolean result = false;
        for (int i = 0; i < this.userRepo.size(); i++) {
            if (this.userRepo.get(i).userId == userId) {
                result = true;
            }
        }
        return result;
    }

    ///////////////////////////////////////////////////////////////////////////////
    public boolean TestBookId(int bookId) {
        boolean result = false;
        for (int i = 0; i < this.bookRepo.size(); i++) {
            if (this.bookRepo.get(i).bookId == bookId) {
                result = true;
                break;
            }
        }
        return result;
    }

    ///////////////////////////////////////////////////////////////////////////////
    public boolean UserTestPassword(int userid) {
        Scanner input = new Scanner(System.in);
        System.out.print("User password required : ");
        String userPassword = input.nextLine();
        return this.userRepo.get(userid - 1).password.equals(userPassword);
    }

    ///////////////////////////////////////////////////////////////////////////////
    public void AddNewBook(String bookName, String bookAuthor) {
        if (!TestPassword()) {
            System.out.println("Password is incorrect :(");
            return;
        }
        Book book = new Book(bookName, bookAuthor, this.booksId, true);
        this.booksId++;
        this.bookRepo.add(book);
        System.out.println("Book id : " + book.bookId);
        System.out.println("*** Book added successfully ***");
        RepoSaver();
    }

    ///////////////////////////////////////////////////////////////////////////////
    public void RemoveBook(int bookId) {
        if (!TestBookId(bookId)) {
            System.out.println("Book not found :(");
            return;
        }
        if (!TestPassword()) {
            System.out.println("Password is incorrect :(");
            return;
        }
        for (int i = 0; i < this.bookRepo.size(); i++) {
            if (this.bookRepo.get(i).bookId == bookId) {
                this.bookRepo.remove(i);
                break;
            }
        }
        System.out.println("*** Book removed successfully ***");
        RepoSaver();
    }

    ///////////////////////////////////////////////////////////////////////////////
    public void AddNewUser(String userName, String phoneNumber, String password) {
        if (!TestPassword()) {
            System.out.println("Password is incorrect :(");
            return;
        }
        User user = new User(userName, phoneNumber, this.usersId, new Date().toString(), password);
        this.userRepo.add(user);
        this.usersId++;
        System.out.println("User id : " + user.userId);
        System.out.println("*** User added successfully ***");
        RepoSaver();
    }

    ///////////////////////////////////////////////////////////////////////////////
    public void RemoveUser(int userId) {
        if (!TestUserId(userId)) {
            System.out.println("User not found :(");
            return;
        }
        if (!TestPassword()) {
            System.out.println("Password is incorrect :(");
            return;
        }
        for (int i = 0; i < this.userRepo.size(); i++) {
            if (this.userRepo.get(i).userId == userId) {
                this.userRepo.remove(i);
                break;
            }
        }
        System.out.println("*** User removed successfully ***");
        RepoSaver();
    }

    ///////////////////////////////////////////////////////////////////////////////
    public void RentBook(int userId, int bookId) {
        if (!TestUserId(userId)) {
            System.out.println("User not found :(");
            return;
        }
        if (!TestBookId(bookId)) {
            System.out.println("Book not found :(");
            return;
        }
        if (!UserTestPassword(userId)) {
            System.out.println("Password is incorrect :(");
            return;
        }
        int bookIndex = 0, userIndex = 0;
        for (int i = 0; i < this.userRepo.size(); i++) {
            if (this.userRepo.get(i).userId == userId) {
                userIndex = i;
            }

        }
        for (int i = 0; i < this.bookRepo.size(); i++) {
            if (this.bookRepo.get(i).bookId == bookId) {
                if (!this.bookRepo.get(i).isAvailable) {
                    System.out.println("Book is already rented :(");
                    return;
                }
                bookIndex = i;
                this.bookRepo.get(i).isAvailable = false;
                break;
            }
        }
        Rent rent = new Rent(this.rentsId, new Date().toString(), this.userRepo.get(userIndex), this.bookRepo.get(bookIndex));
        this.rentRepo.add(rent);
        System.out.println("*** Book rented successfully ***");
        RepoSaver();
    }

    ///////////////////////////////////////////////////////////////////////////////
    public void ReturnBook(int userId, int bookId) {
        if (!TestUserId(userId)) {
            System.out.println("User not found :(");
            return;
        }
        if (!TestBookId(bookId)) {
            System.out.println("Book not found :(");
            return;
        }
        for (int i = 0; i < this.rentRepo.size(); i++) {
            if (this.rentRepo.get(i).book.bookId == bookId && this.rentRepo.get(i).user.userId == userId) {
                for (int j = 0; j < this.bookRepo.size(); j++) {
                    if (this.bookRepo.get(j).bookId == bookId) {
                        this.bookRepo.get(j).isAvailable = true;
                        break;
                    }
                }
                this.rentRepo.remove(i);
                break;
            }
        }
        System.out.println("*** Book returned successfully ***");
        RepoSaver();
    }

    ///////////////////////////////////////////////////////////////////////////////
    public void ShowBookList() {
        if (this.bookRepo.isEmpty()) {
            System.out.println("Book list is empty :(");
            return;
        }
        System.out.println("------------------------------------------------------------------");
        for (int i = 0; i < this.bookRepo.size(); i++) {
            System.out.println("Book[" + (i + 1) + "]");
            System.out.println("Book name    : " + this.bookRepo.get(i).bookName);
            System.out.println("Book author  : " + this.bookRepo.get(i).bookAuthor);
            System.out.println("Book id      : " + this.bookRepo.get(i).bookId);
            System.out.println("Availability : " + this.bookRepo.get(i).isAvailable);
            System.out.println("------------------------------------------------------------------");
        }
    }

    ///////////////////////////////////////////////////////////////////////////////
    public void ShowUserList() {
        if (!TestPassword()) {
            System.out.println("Password is incorrect :(");
            return;
        }
        if (this.userRepo.isEmpty()) {
            System.out.println("User list is empty :(");
            return;
        }
        System.out.println("------------------------------------------------------------------");
        for (int i = 0; i < this.userRepo.size(); i++) {
            System.out.println("User[" + (i + 1) + "]");
            System.out.println("User name          : " + this.userRepo.get(i).userName);
            System.out.println("User phone number  : " + this.userRepo.get(i).phoneNumber);
            System.out.println("User id            : " + this.userRepo.get(i).userId);
            System.out.println("User password      : " + this.userRepo.get(i).password);
            System.out.println("User register date : " + this.userRepo.get(i).registerDate);
            System.out.println("------------------------------------------------------------------");
        }
    }

    ///////////////////////////////////////////////////////////////////////////////
    public void ShowRentList() {
        if (!TestPassword()) {
            System.out.println("Password is incorrect :(");
            return;
        }
        if (this.rentRepo.isEmpty()) {
            System.out.println("Rent list is empty :(");
            return;
        }
        System.out.println("------------------------------------------------------------------");
        for (int i = 0; i < this.rentRepo.size(); i++) {
            System.out.println("Rent[" + (i + 1) + "]");
            System.out.println("Rent id      : " + this.rentRepo.get(i).rentId);
            System.out.println("Rent date    : " + this.rentRepo.get(i).rentDate);
            System.out.println("Rent user id : " + this.rentRepo.get(i).user.userId);
            System.out.println("Rent book id : " + this.rentRepo.get(i).book.bookId);
            System.out.println("------------------------------------------------------------------");
        }
    }

    ///////////////////////////////////////////////////////////////////////////////
    public void ShowOptions() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("# Add options : ");
        System.out.println("lib:add:book:<bookName>:<bookAuthor>");
        System.out.println("lib:add:user:<userName>:<phoneNumber>:<password>");
        System.out.println("-----------------------------------------------------------");
        System.out.println("# Remove options : ");
        System.out.println("lib:remove:book:<bookID>");
        System.out.println("lib:remove:user:<userID>");
        System.out.println("-----------------------------------------------------------");
        System.out.println("# Show lists : ");
        System.out.println("lib:show:bookList");
        System.out.println("lib:show:userList");
        System.out.println("lib:show:rentList");
        System.out.println("-----------------------------------------------------------");
        System.out.println("# Rent & Return : ");
        System.out.println("lib:rent:book:<userID>:<bookID> ");//no pass needed
        System.out.println("lib:return:book:<userID>:<bookID>");//no pass needed
        System.out.println("-----------------------------------------------------------");
    }

    ///////////////////////////////////////////////////////////////////////////////
    public boolean CheckCommand(String command) {
        command = command.toLowerCase();
        command = command.trim();
        if (command.equals("fuck")
                || command.equals("how are you")
                || command.equals("i love you")
                || command.equals("help")
                || command.equals("exit")
                || command.equals("hello")
                || command.equals("hi")
                || command.equals("mew")
                || command.equals("meow")
                || command.equals("fuck you")
                || command.equals("fuckyou")
                || command.equals("fckyou")
                || command.equals("fukyou")
                || command.equals("fck you")
                || command.equals("fuk you")
                || command.equals("fck")
                || command.equals("fuk")) {
            return true;
        }
        String[] c2 = command.split(":");
        if (c2.length < 3) {
            return false;
        }
        String c3 = c3 = c2[0] + ":" + c2[1] + ":" + c2[2];
        boolean x1 = c3.equals("lib:add:book") || c3.equals("lib:remove:book") || c3.equals("lib:rent:book") ||
                c3.equals("lib:return:book") || c3.equals("lib:show:booklist") || c3.equals("lib:add:user") ||
                c3.equals("lib:remove:user") || c3.equals("lib:show:userlist") || c3.equals("lib:add:admin") ||
                c3.equals("lib:remove:admin") || c3.equals("lib:show:adminlist") || c3.equals("lib:show:rentlist");
        if (!x1) {
            return false;
        }
        if (c3.equals("lib:add:book") && c2.length != 5) {
            return false;
        }
        if (c3.equals("lib:remove:book") && c2.length != 4) {
            return false;
        }
        if (c3.equals("lib:rent:book") && c2.length != 5) {
            return false;
        }
        if (c3.equals("lib:return:book") && c2.length != 5) {
            return false;
        }
        if (c3.equals("lib:show:booklist") && c2.length != 3) {
            return false;
        }
        if (c3.equals("lib:add:user") && c2.length != 6) {
            return false;
        }
        if (c3.equals("lib:remove:user") && c2.length != 4) {
            return false;
        }
        if (c3.equals("lib:show:userlist") && c2.length != 3) {
            return false;
        }
        if (c3.equals("lib:add:admin") && c2.length != 6) {
            return false;
        }
        if (c3.equals("lib:remove:admin") && c2.length != 4) {
            return false;
        }
        if (c3.equals("lib:show:adminlist") && c2.length != 3) {
            return false;
        }
        if (c3.equals("lib:show:rentlist") && c2.length != 3) {
            return false;
        }
        return true;
    }

    public void RunByCommand() {
        Welcome();
        for (; ; ) {
            Scanner input = new Scanner(System.in);
            String command;
            System.out.println("## Write a command and press [Enter] : ");
            for (; ; ) {
                System.out.print(">>> ");
                command = input.nextLine();
                if (CheckCommand(command)) {
                    break;
                }
                System.out.println("Command not found :(");
                System.out.println("## Write a command and press [Enter] : ");
            }
            command = command.toLowerCase();
            command = command.trim();
            switch (command) {
                case "fuck":
                case "fck":
                case "fuk": {
                    System.out.println("Don't be rude son");
                    continue;
                }
                case "i love you": {
                    System.out.println("I love you too honey :)");
                    continue;
                }
                case "how are you": {
                    System.out.println("Non of your business :|");
                    continue;
                }
                case "help": {
                    ShowOptions();
                    continue;
                }
                case "mew":
                case "meow": {
                    System.out.println("What a lovely kitten :)");
                    continue;
                }
                case "hello":
                case "hi": {
                    System.out.println("Hello baby ;)");
                    continue;
                }
                case "fuck you":
                case "fuckyou":
                case "fckyou":
                case "fukyou":
                case "fuk you":
                case "fck you": {
                    System.out.println("Fuck you too :|");
                    continue;
                }
                case "exit": {
                    return;
                }
            }
            String[] c2 = command.split(":");
            String c3 = c2[0] + ":" + c2[1] + ":" + c2[2];
            switch (c3) {
                case "lib:add:book": {
                    AddNewBook(c2[3], c2[4]);
                    break;
                }
                case "lib:remove:book": {
                    RemoveBook(Integer.parseInt(c2[3]));
                    break;
                }
                case "lib:rent:book": {
                    RentBook(Integer.parseInt(c2[3]), Integer.parseInt(c2[4]));
                    break;
                }
                case "lib:return:book": {
                    ReturnBook(Integer.parseInt(c2[3]), Integer.parseInt(c2[4]));
                    break;
                }
                case "lib:show:booklist": {
                    ShowBookList();
                    break;
                }
                case "lib:add:user": {
                    AddNewUser(c2[3], c2[4], c2[5]);
                    break;
                }
                case "lib:remove:user": {
                    RemoveUser(Integer.parseInt(c2[3]));
                    break;
                }
                case "lib:show:userlist": {
                    ShowUserList();
                    break;
                }
                case "lib:show:rentlist": {
                    ShowRentList();
                    break;
                }
                default: {
                    continue;
                }
            }
        }
    }
}

