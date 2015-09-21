import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class Controller {

	public static void main(String[] args) {
		JSONParser parser = new JSONParser();
		Map<Integer, Integer> mapped_kw = new HashMap<Integer, Integer>();

		try {
			System.out.println("Reading JSON file from Java program");
			FileReader fileReader = new FileReader("input.json");
			JSONObject json = (JSONObject) parser.parse(fileReader);
			JSONArray myJsonArray = (JSONArray) json.get("videos");

			MaxentTagger tagger = new MaxentTagger("taggers/left3words-wsj-0-18.tagger");

			Class.forName("org.sqlite.JDBC");
			Connection dbConn = DriverManager.getConnection("jdbc:sqlite:Ad_Picker_db.db");
			// System.out.println("Opened DB successfully");
			Statement stmt = dbConn.createStatement();
			ResultSet resultSet = stmt.executeQuery("SELECT Category_ID ID, Category_NAME Name, Keywords FROM Dictionary");

			while (resultSet.next()) {
				for (int i = 0; i < myJsonArray.size(); i++) {
					JSONObject myJObject = (JSONObject) myJsonArray.get(i);
					JSONObject snippet = (JSONObject) myJObject.get("snippet");

					// Tag words
					String taggedTitle = tagger.tagTokenizedString(snippet.get("title").toString());
					// System.out.println("Tagged Title:" + taggedTitle);

					// Get desired keywords
					String[] taggedWords = taggedTitle.split(" ");
					List<String> keywords = new ArrayList<String>();
					for (String tt : taggedWords) {
						if (tt.contains("_NNP") || tt.contains("_NN") || tt.contains("_NNPS") || tt.contains("_NNS") || tt.contains("_CD")
								|| tt.contains("_JJ") || tt.contains("_JJS") || tt.contains("_JJR")) {
							String[] tagged_kw = tt.split("_NNP|_NN|_NNPS|_NNS|_CD|_JJ|_JJR|_JJS");
							keywords.add(tagged_kw[0]);

							String[] kw = resultSet.getString("Keywords").split(",");
							if (Arrays.stream(kw).filter(s -> s.compareToIgnoreCase(tagged_kw[0]) == 0).toArray().length > 0) {
								int cat_id = resultSet.getInt("ID");
								int count = mapped_kw.getOrDefault(cat_id, 0);
								mapped_kw.put(cat_id, ++count);
							}
						}
					}
				}
			}

			List<Entry<Integer, Integer>> sortedMap = mapped_kw.entrySet().stream().sorted(Collections.reverseOrder(Entry.comparingByValue()))
					.collect(Collectors.toList());

			System.out.println("Sorted Map: " + Arrays.toString(sortedMap.toArray()));

			String top3_cat_ids = "";
			for (int i = 0; i < 3 && i < sortedMap.size(); i++) {
				@SuppressWarnings("unchecked")
				Entry<Integer, Integer> pair = (Entry<Integer, Integer>) sortedMap.toArray()[i];
				top3_cat_ids += pair.getKey() + ",";
			}

			top3_cat_ids = top3_cat_ids.length() > 0 ? top3_cat_ids.substring(0, top3_cat_ids.length() - 1) : top3_cat_ids;

			resultSet = stmt.executeQuery("SELECT Image_path path FROM Ads WHERE Category_ID in (" + top3_cat_ids + ")");

			while (resultSet.next()) {
				System.out.println(resultSet.getString("path"));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}