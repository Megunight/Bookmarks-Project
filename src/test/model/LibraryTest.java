package model;

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
    void removeBookTest() {
        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);

        library.removeBook("1984");
        assertFalse((library.getLibrary()).contains(b2));

        library.removeBook("interesting");
        assertFalse((library.getLibrary()).contains(b1));

        library.removeBook("Destiny Forged");
        assertTrue((library.getLibrary()).contains(b3));
    }

    /*
    @Test
    void printLibraryTest() {
        library.addBook(b3);
        library.addBook(b2);
        library.addBook(b1);

        assertEquals("\n" +
                "Title: Forged Destiny\n" +
                "Author: Coeur Al'Aran\n" +
                "Page Count: 3000\n" +
                "Pages Read: 200\n" +
                "Finished: false\n" +
                "Rating: 0\n" +
                "\n" +
                "Title: 1984\n" +
                "Author: George Orwell\n" +
                "Page Count: 328\n" +
                "Pages Read: 328\n" +
                "Finished: true\n" +
                "Rating: 0\n" +
                "\n" +
                "Title: Interesting\n" +
                "Author: God\n" +
                "Page Count: 2000\n" +
                "Pages Read: 0\n" +
                "Finished: false\n" +
                "Rating: 0", systemOutRule.getLog());
    }
     */
}
