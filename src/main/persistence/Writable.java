package persistence;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Writable {
    // EFFECTS: returns this as JSON object
    public abstract JSONObject toJson();

    // EFFECTS: returns fields in object as JSON array
    public JSONArray objectToJson(Writable writable) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(writable.toJson());
        return jsonArray;
    }
}
