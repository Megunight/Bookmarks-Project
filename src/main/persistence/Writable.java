package persistence;

import org.json.JSONObject;

//Taken from JSON demo
public interface Writable {
    //EFFECTS: returns this as JSON object
    JSONObject toJson();
}