package model;

import java.util.ArrayList;
import java.util.List;

// represents a list of book objects
public class Library {
    private List<Book> library;

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

    public List<Book> getLibrary() {
        return library;
    }

    public int getIndexofBook(String bookTitle) {
        assert library.size() > 0;
        int index = -1;
        for (int i = 0; i < library.size(); i++) {
            if (library.get(i).getTitle().equalsIgnoreCase(bookTitle)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
