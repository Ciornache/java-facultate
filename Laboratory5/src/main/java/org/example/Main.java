package org.example;


import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static Catalog catalog = new Catalog();

    public static void main(String[] args) throws IncorrectCommandName, IOException {
        while (true)
        {
            String command = scanner.nextLine();
            if(command.equals("finish"))
                break;

            try {
                catalog.process(command);
            }
            catch(Exception e) {
                String text = e.getMessage();
                if(e.getClass() == NullPointerException.class)
                    System.out.println("NULL POINTER EXCEPTION");
                else
                    System.out.println(text);
            }
        }
    }

    public static void changeCatalog(Catalog catalog) {
        Main.catalog = catalog;
    }


}