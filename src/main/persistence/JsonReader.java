package persistence;

import exceptions.SameTitleException;
import model.Book;
import model.Library;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

//Taken from JSON demo
// Represents a reader that reads library from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads library from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Library read() throws IOException, SameTitleException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLibrary(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses library from JSON object and returns it
    private Library parseLibrary(JSONObject jsonObject) throws SameTitleException {
        Library library = new Library();
        addBooks(library, jsonObject);
        return library;
    }

    // MODIFIES: library
    // EFFECTS: parses books from JSON object and adds them to library
    private void addBooks(Library library, JSONObject jsonObject) throws SameTitleException {
        JSONArray jsonArray = jsonObject.getJSONArray("books");
        for (Object json : jsonArray) {
            JSONObject nextBook = (JSONObject) json;
            addBook(library, nextBook);
        }
    }

    // MODIFIES: library
    // EFFECTS: parses books from JSON object and adds it to library
    private void addBook(Library library, JSONObject jsonObject) throws SameTitleException {
        String title = jsonObject.getString("title");
        String author = jsonObject.getString("author");
        int pageNum = jsonObject.getInt("page number");
        int pagesRead = jsonObject.getInt("pages read");
        int rating = jsonObject.getInt("rating");
        String genre = jsonObject.getString("genre");
        Book b = new Book(title, author, pageNum, pagesRead, genre);
        b.setRating(rating);
        library.addBook(b);
    }
}
