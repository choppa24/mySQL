package utils;

import enums.Paths;
import enums.Resources;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonUtility {
    private static final String filePath = Paths.RESOURCES.toString() + Resources.TEST_JSON.toString();
    private static final String data = "testData";

    public static Map<String, Object> getJsonObject() {
        JSONParser jsonParser = new JSONParser();
        Map<String, Object> testData = null;
        try {
            Object json = jsonParser.parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) json;
            testData = toMap((JSONObject) jsonObject.get(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return testData;
    }

    public static Map<String, Object> toMap(JSONObject jsonobj) {
        Map<String, Object> map = new HashMap<>();
        Iterator<String> keys = jsonobj.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            Object value = jsonobj.get(key);
            if (value instanceof JSONArray) {
                value = ((JSONArray) value).toList();
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }
}
