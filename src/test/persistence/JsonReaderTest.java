package persistence;

import exceptions.SameTitleException;
import model.Book;
import model.Library;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Library library = reader.read();
            Assertions.fail("IOException expected");
        } catch (IOException | SameTitleException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyLibrary() {
        JsonReader reader = new JsonReader("./data/emptylibrary.json");
        try {
            Library library = reader.read();
            List<Book> test = library.getLibrary();
            Assertions.assertTrue(test.isEmpty());
        } catch (IOException e) {
            Assertions.fail("Couldn't read from file");
        } catch (SameTitleException e) {
            Assertions.fail();
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/librarytest.json");
        try {
            Library library = reader.read();
            List<Book> test = library.getLibrary();
            Assertions.assertEquals(2, test.size());
            checkBook("1984", "George Orwell", 328, 28, 5, "Dystopian", test.get(0));
            checkBook("Interesting", "God", 222, 222, 0, "Philosophy", test.get(1));
        } catch (IOException e) {
            Assertions.fail("Couldn't read from file");
        } catch (SameTitleException e) {
            Assertions.fail();
        }
    }
}