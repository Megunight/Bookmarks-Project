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
        assertEquals("God", b1.getAuthor());
        assertEquals("Philosophy", b1.getGenre());
        assertEquals(2000, b1.getPageNum());
        assertEquals(0, b1.getPagesRead());
        assertEquals("Interesting", b1.getTitle());
        b1.setPagesRead(500);
        assertEquals(500, b1.getPagesRead());
        b1.setPagesRead(2000);
        assertTrue(b1.isCompleted());
    }

    @Test
    void setRatingTest() {
        b1.setRating(5);
        assertEquals(5, b1.getRating());
        b1.setRating(0);
        assertEquals(0, b1.getRating());
        b1.setRating(3);
        assertEquals(3, b1.getRating());

        boolean exceptionThrown1 = false;
        try {
            b2.setRating(-1);
        } catch (AssertionError e) {
            exceptionThrown1 = true;
        }
        assertTrue(exceptionThrown1);
        boolean exceptionThrown2 = false;
        try {
            b2.setRating(6);
        } catch (AssertionError e) {
            exceptionThrown2 = true;
        }
        assertTrue(exceptionThrown2);
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

    @Test
    void pagesReadTodayTest() {
        assertThrows(AssertionError.class, () -> b1.setPagesReadToday(-1));
        assertEquals(0, b1.getPagesReadToday());
        b1.setPagesReadToday(10);
        assertEquals(10, b1.getPagesReadToday());
    }

    @Test
    void daysLeftTest() {
        assertThrows(AssertionError.class, () -> b1.getDaysLeft());
        b1.setPagesReadToday(10);
        assertEquals(200, b1.getDaysLeft());
    }
}
