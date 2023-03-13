package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import exceptions.BookNotFoundException;
import exceptions.SameTitleException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// represents a list of book objects
public class Library implements Writable {
    private List<Book> library;
    private int dailyReadingGoal;
    private int dailyReadingAccum;
    private String name;

    public Library() {
        library = new ArrayList<Book>();
    }

    //MODIFIES: this
    //EFFECTS: adds a book to the library
    public void addBook(Book book) throws SameTitleException {
        for (Book b: library) {
            if (b.getTitle().equalsIgnoreCase(book.getTitle())) {
                throw new SameTitleException("Book with title already exists");
            }
        }
        library.add(book);
    }

    //MODIFIES: this
    //EFFECTS: sets daily reading goal
    public void setDailyReadingGoal(int dailyReadingGoal) {
        assert dailyReadingGoal >= 0;
        this.dailyReadingGoal = dailyReadingGoal;
    }

    //MODIFIES: this
    //EFFECTS: sets daily reading goal that has been accumulated
    public void setDailyReadingAccum(int dailyReadingAccum) {
        this.dailyReadingAccum = dailyReadingAccum;
    }

    public int getDailyReadingGoal() {
        return dailyReadingGoal;
    }

    public int getDailyReadingAccum() {
        return dailyReadingAccum;
    }

    //MODIFIES: this
    //EFFECTS: removes the book with the given title from the library
    //TODO: add exception handling for when given a title that is not in the library
    public void removeBook(String bookTitle) throws BookNotFoundException {
        boolean cannotFind = true;
        for (Book b: library) {
            if (bookTitle.equalsIgnoreCase(b.getTitle())) {
                library.remove(b);
                cannotFind = false;
            }
        }
        if (cannotFind) {
            throw new BookNotFoundException("Book with title cannot be found within the library");
        }
    }

    public List<Book> getLibrary() {
        return library;
    }

    //EFFECTS: returns the index of the book with given parameter in the library
    public int getIndexOfBook(String bookTitle) throws BookNotFoundException {
        int index = -1;
        for (int i = 0; i < library.size(); i++) {
            if (library.get(i).getTitle().equalsIgnoreCase(bookTitle)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new BookNotFoundException("Book with title cannot be found within the library");
        }
        return index;
    }

    //MODIFIES: this
    //EFFECTS: sorts the library in terms of rating in descending order
    public void sortLibrary() {
        Collections.sort(library, Collections.reverseOrder());
    }

    //Taken from JSON demo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("books", libraryToJson());
        return json;
    }

    //Taken from JSON demo
    //EFFECTS: returns things in this workroom as a JSON array
    private JSONArray libraryToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book b : library) {
            jsonArray.put(b.toJson());
        }

        return jsonArray;
    }
}
