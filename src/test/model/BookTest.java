package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    private Book b1;
    private Book b2;
    private Book b3;

    @BeforeEach
    void setup() {
        b1 = new Book("Interesting", "God", 2000, 0, "Philosophy");
        b2 = new Book("1984", "George Orwell", 328, 328, "Dystopian");
        b3 = new Book("Forged Destiny", "Coeur Al'Aran", 3000, 200, "Adventure");
    }

    @Test
    void BookConstructorTest() {
        assertTrue(b2.isCompleted());
        assertFalse(b1.isCompleted());
        assertFalse(b3.isCompleted());
    }

    @Test
    void setRatingTest() {
        b1.setRating(5);
        assertEquals(5, b1.getRating());

        boolean exceptionThrown = false;
        try {
            b2.setRating(-1);
        } catch (AssertionError e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    void compareToTest() {
        b1.setRating(5);
        b2.setRating(0);
        b3.setRating(0);
        assertEquals(1, b1.compareTo(b2));
        assertEquals(-1, b2.compareTo(b1));
        assertEquals(0, b2.compareTo(b3));
    }
}
