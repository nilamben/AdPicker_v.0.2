import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonReader {
	public static Map<String, Integer> inputData = new HashMap<String, Integer>();

	public static void main(String[] args) throws Exception {
		JSONParser parser = new JSONParser();

		try {
			System.out.println("Reading JSON file from Java program");
			FileReader fileReader = new FileReader("C:\\Users\\Nilamben\\workspace_J2ee\\AdPicker\\input.json");
			JSONObject json = (JSONObject) parser.parse(fileReader);

			JSONArray myJsonArray = (JSONArray) json.get("videos");
			for (int i = 0; i < myJsonArray.size(); i++) {
				JSONObject myJObject = (JSONObject) myJsonArray.get(i);
				JSONObject snippet = (JSONObject) myJObject.get("snippet");
				String title = snippet.get("title").toString();
				int catId = Integer.parseInt(snippet.get("categoryId").toString());
				System.out.println("Title :" + title.toString() + " Id: " + catId);

				inputData.put(title, catId);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
