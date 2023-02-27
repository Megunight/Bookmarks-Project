package model;

import exceptions.BookNotFoundException;
import exceptions.SameTitleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library library;
    private Book b1;
    private Book b2;
    private Book b3;

    @BeforeEach
    void setup() {
        library = new Library();
        b1 = new Book("Interesting", "God", 2000, 0, "Philosophy");
        b2 = new Book("1984", "George Orwell", 328, 328, "Dystopian");
        b3 = new Book("Forged Destiny", "Coeur Al'Aran", 3000, 200, "Adventure");
    }

    @Test
    void removeBookTest() throws BookNotFoundException, SameTitleException {
        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);

        library.removeBook("1984");
        assertFalse((library.getLibrary()).contains(b2));

        library.removeBook("interesting");
        assertFalse((library.getLibrary()).contains(b1));

        boolean exceptionThrown = false;
        try {
            library.removeBook("Destiny Forged");
        } catch (BookNotFoundException e) {
            exceptionThrown = true;
        }
        assertTrue((library.getLibrary()).contains(b3));
        assertTrue(exceptionThrown);
    }

    @Test
    void getIndexOfBookTest() throws SameTitleException, BookNotFoundException{
        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);

        assertEquals(0, library.getIndexOfBook("Interesting"));
        assertEquals(1, library.getIndexOfBook("1984"));
        assertEquals(2, library.getIndexOfBook("Forged Destiny"));

        boolean exceptionThrown = false;
        try {
            library.getIndexOfBook("God");
        } catch (BookNotFoundException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    void sortLibraryTest() throws SameTitleException, BookNotFoundException {
        b2.setRating(5);
        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);
        library.sortLibrary();

        assertEquals(0, library.getIndexOfBook("1984"));
        assertEquals(1, library.getIndexOfBook("Interesting"));
        assertEquals(2, library.getIndexOfBook("Forged Destiny"));
    }
}
