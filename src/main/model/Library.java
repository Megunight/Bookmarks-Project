package model;

import java.util.ArrayList;

// represents a list of book objects
public class Library {
    private ArrayList<Book> library;

    public Library() {
        library = new ArrayList<Book>();
    }

    public void addBook(Book book) {
        library.add(book);
    }

    //REQUIRES: bookTitle to be a title of a book in the library
    //MODIFIES: this
    //EFFECTS: removes the book with the given title from the library
    //TODO: add exception handling for when given a title that is not in the library
    public void removeBook(String bookTitle) {
        for (Book b: library) {
            if (bookTitle.equalsIgnoreCase(b.getTitle())) {
                library.remove(b);
            }
        }
    }

    public ArrayList<Book> getLibrary() {
        return library;
    }

    //TODO: put this method in ui instead
    //EFFECTS: prints out to console all the books in library with each book's info
    //note: this method is for the console version
    public void printLibrary() {
        for (Book b: library) {
            System.out.println();
            System.out.println("Title: " + b.getTitle());
            System.out.println("Author: " + b.getAuthor());
            System.out.println("Page Count: " + b.getPageNum());
            System.out.println("Pages Read: " + b.getPagesRead());
            System.out.println("Finished: " + b.isCompleted());
            System.out.println("Rating: " + b.getRating());
        }
    }
}
