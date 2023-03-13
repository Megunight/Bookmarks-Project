package persistence;

import exceptions.SameTitleException;
import model.Book;
import model.Library;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Library library = new Library();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyLibrary() {
        try {
            Library library = new Library();
            JsonWriter writer = new JsonWriter("./data/emptylibrary.json");
            writer.open();
            writer.write(library);
            writer.close();

            JsonReader reader = new JsonReader("./data/emptylibrary.json");
            library = reader.read();
            List<Book> test = library.getLibrary();
            assertTrue(test.isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (SameTitleException e) {
            fail();
        }
    }

    @Test
    void testWriterGeneralLibrary() {
        try {
            Library library = new Library();
            library.addBook(new Book("1984", "George Orwell", 328, 28, "Dystopian"));
            library.addBook(new Book("Interesting", "God", 222, 222, "Philosophy"));
            JsonWriter writer = new JsonWriter("./data/writerlibrarytest.json");
            writer.open();
            writer.write(library);
            writer.close();

            JsonReader reader = new JsonReader("./data/writerlibrarytest.json");
            library = reader.read();
            List<Book> test = library.getLibrary();
            assertEquals(2, test.size());
            checkBook("1984", "George Orwell", 328, 28, 0, "Dystopian", test.get(0));
            checkBook("Interesting", "God", 222, 222, 0, "Philosophy", test.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (SameTitleException e) {
            fail();
        }
    }
}