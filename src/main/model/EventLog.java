package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// Represents a list of events
public class EventLog implements Iterable<Event> {
    private static EventLog theLog;
    private Collection<Event> events;

    // EFFECTS: creates an empty event log, can only be accessed within the class
    private EventLog() {
        events = new ArrayList<>();
    }

    // EFFECTS: returns the instance of the event log, creates it if it doesn't already exist
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }
        return theLog;
    }

    // MODIFIES: this
    // EFFECTS: adds an event to the event log
    public void logEvent(Event e) {
        events.add(e);
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
