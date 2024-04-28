package org.example;

import java.io.File;

public class MainApp {
    public static void main(String[] args) {
        try {
            File f1 = new File("BookRepo.txt");
            File f2 = new File("UserRepo.txt");
            File f3 = new File("AdminRepo.txt");
            File f4 = new File("RentRepo.txt");
            f1.createNewFile();
            f2.createNewFile();
            f3.createNewFile();
            f4.createNewFile();
        } catch (Exception e) {
            System.out.println(" Error : " + e);
        }
        Library lib = new Library("7:30 am - 11 pm", "Kourosh Lib", "12345", 100000);
        lib.RunByCommand();
    }
}
