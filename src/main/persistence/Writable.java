package persistence;

import org.json.JSONObject;

// Interface that forces class to have a way to represent its data in a JSONObject.
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
