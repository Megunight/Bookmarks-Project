package ui;

import java.util.Scanner;

import static jdk.internal.vm.compiler.word.LocationIdentity.init;


//console-based application for library
public class LibraryApp {
    private Scanner input;

    public LibraryApp() {
        runLibrary();
    }

    private void runLibrary() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();

            if (command.equalsIgnoreCase("quit")) {
                keepGoing = false;
            } else {
                process(command);
            }
        }
        System.out.println("Exited successfully");
    }

    private void displayMenu() {
        System.out.println("Welcome to your library. To get started, here are the commands:");
        System.out.println("help - displays this menu again");
        System.out.println("view - views the current library");
        System.out.println("genre - switches view to sort library by genre");
        System.out.println("add - adds a book to the library");
        System.out.println("remove - removes a book from the library");
        System.out.println("rate - allows user to give rating to a book");
        System.out.println("quit - quit the program");
    }

    private void process(String command) {
        switch (command) {
            case "help" :
                displayMenu();
                break;
            case "view" :
                break;
            case "genre" :
                System.out.println("Type the genre you want to sort by");
                System.out.println("all - for switching back to default view");
                break;
            case "add" :
                System.out.println("Type the title, author, number of pages, number of pages read, and genre of the book separated by commas");
                System.out.println("eg. : 1984, George Orwell, 328, 28, Dystopian");
                break;
            case "remove" :
                System.out.println("Type the title of the book you'd like to remove");
                break;
            case "rate" :
                System.out.println("Type the title of the book you'd like to rate and the rating as an integer between 1 and 5");
                System.out.println("eg. : 1984, 5");
                break;
            case "quit" :
                break;
            default :
                System.out.println("Seems like I didn't get that, please try again");
                break;
        }
    }
}
