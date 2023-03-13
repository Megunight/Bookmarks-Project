package persistence;

import model.Library;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriter {
    private PrintWriter writer;
    private String destination;

    public JsonWriter(String destination) {
        this.destination = destination;
    }

    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    //Taken from JSON demo
    //MODIFIES: this
    //EFFECTS: writes JSON representation of workroom to file
    public void write(Library library) {
        JSONObject json = library.toJson();
        saveToFile(json.toString());
    }

    public void close() {
        writer.close();
    }

    public void saveToFile(String json) {
        writer.print(json);
    }
}
