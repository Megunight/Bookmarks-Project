package ui;

import exceptions.BookNotFoundException;
import exceptions.SameTitleException;
import model.Book;
import model.Library;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//console-based application for library
public class LibraryApp {
    private Scanner input;
    private Library library;
    private List<Book> currentView;
    private String currentGenre = "all";

    public LibraryApp() {
        runLibrary();
    }

    private void runLibrary() {
        boolean keepGoing = true;
        String command = null;

        init();
        displayMenu();
        while (keepGoing) {
            command = input.nextLine();

            if (command.equalsIgnoreCase("quit")) {
                keepGoing = false;
            } else {
                process(command);
            }
        }
        System.out.println("Exited successfully");
    }

    private void init() {
        library = new Library();
        input = new Scanner(System.in);
        currentView = new ArrayList<Book>();
        currentView.addAll(library.getLibrary());
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
            case "help":
                displayMenu();
                break;
            case "view":
                view();
                break;
            case "genre":
                System.out.println("Type the genre you want to sort by");
                System.out.println("all - for switching back to default view");
                setCurrentView();
                break;
            case "add":
                System.out.println("Type the title, author, number of pages, number of pages read, and genre of the book separated by commas");
                System.out.println("eg. : 1984, George Orwell, 328, 28, Dystopian");
                try {
                    add();
                } catch (RuntimeException | AssertionError e) {
                    System.out.println("There is a problem with your input");
                }
                break;
            case "remove":
                System.out.println("Type the title of the book you'd like to remove");
                remove();
                break;
            case "rate":
                System.out.println("Type the title of the book you'd like to rate and the rating as an integer between 0 and 5");
                System.out.println("eg. : 1984, 5");
                try {
                    rate();
                } catch (RuntimeException | AssertionError e) {
                    System.out.println("There is a problem with your input");
                }
                break;
            case "quit":
                break;
            default:
                System.out.println("Seems like I didn't get that, please try again"); //TODO: can replace with exception
                break;
        }
    }

    private void setCurrentView() {
        String genre = input.nextLine();
        currentGenre = genre;
        setCurrentViewForMethods();
    }

    private void setCurrentViewForMethods() {
        currentView = new ArrayList<Book>();
        currentView.addAll(library.getLibrary());

        if (!currentGenre.equalsIgnoreCase("all")) {
            currentView.removeIf(book -> !(book.getGenre()).equalsIgnoreCase(currentGenre));
        }
    }

    //EFFECTS: prints out to console all the books in currentView with each book's info
    private void view() {
        for (Book b: currentView) {
            System.out.println();
            System.out.println("Title: " + b.getTitle());
            System.out.println("Author: " + b.getAuthor());
            System.out.println("Page Count: " + b.getPageNum());
            System.out.println("Pages Read: " + b.getPagesRead());
            System.out.println("Finished: " + b.isCompleted());
            System.out.println("Genre: " + b.getGenre());
            System.out.println("Rating: " + b.getRating());
        }
    }

    private void add() throws NumberFormatException, AssertionError {
        String userInput = input.nextLine();
        ArrayList<String> bookInfo = separateInput(userInput);
        assert bookInfo.size() == 5;

        Book book = new Book(bookInfo.get(0), bookInfo.get(1), Integer.parseInt(bookInfo.get(2)), Integer.parseInt(bookInfo.get(3)), bookInfo.get(4));
        try {
            library.addBook(book);
        } catch (SameTitleException e) {
            System.out.println(e.getMessage());
        }
        setCurrentViewForMethods();
    }

    private void remove() {
        String bookTitle = input.nextLine();
        try {
            library.removeBook(bookTitle);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
        setCurrentViewForMethods();
    }

    private void rate() throws NumberFormatException, AssertionError {
        String userInput = input.nextLine();
        ArrayList<String> titleRate = separateInput(userInput);
        assert titleRate.size() == 2;

        try {
            int index = library.getIndexOfBook(titleRate.get(0));
            Book book = library.getLibrary().get(index);
            book.setRating(Integer.parseInt(titleRate.get(1)));
            library.sortLibrary();
            setCurrentViewForMethods();
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private ArrayList<String> separateInput(String userInput) {
        String[] split = userInput.split(", ", 0);
        ArrayList<String> inputs = new ArrayList<String>();

        for (String s : split) {
            if (!s.isBlank()) {
                inputs.add(s);
            }
        }
        return inputs;
    }
}
