package persistence;

import model.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkBook(String title, String author, int pageNum, int pagesRead, int rating, String genre, Book b) {
        assertEquals(title, b.getTitle());
        assertEquals(author, b.getAuthor());
        assertEquals(pageNum, b.getPageNum());
        assertEquals(pagesRead, b.getPagesRead());
        assertEquals(rating, b.getRating());
        assertEquals(genre, b.getGenre());
    }
}