package org.example;


import java.io.IOException;
import java.util.Scanner;


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
            catch(NullPointerException e) {
                System.out.println("NULL POINTER EXCEPTION");
            }
            catch(RedundantObjectException e) {
                System.out.println("OBJECT ALREADY PRESENT IN THE CATALOG");
            }
            catch(InvalidDocumentName e) {
                System.out.println("OBJECT NOT PRESENT IN THE CATALOG");
            }
            catch (IncorrectCommandName e) {
                System.out.println("WRONG COMMAND");
                System.out.print("ONLY USABLE COMMANDS ARE ");
                for(String comm : catalog.getCommandHandler().getCommandList())
                    System.out.print(comm.toUpperCase() + " ");
                System.out.println();
            }
            catch(DocumentFormatException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
        System.gc();
    }

    public static void changeCatalog(Catalog catalog) {
        Main.catalog = catalog;
    }

}