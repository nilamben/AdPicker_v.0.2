package src.com;

import java.io.FileReader;
import java.io.File;
import java.io.IOException;
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
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

@WebServlet("/AdFinalServlet")
public class AdFinalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Map<String, Integer> inputData = new HashMap<String, Integer>();
	static List<String> titles = new ArrayList<String>();
	static boolean inputReadFile = true;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Create db if not exist
		String dbpath = getServletContext().getRealPath("Ad_Picker_db.db");
		if(!(new File(dbpath).exists()))
			DbConnect.createDb(dbpath);
		
		// Fetching data from UI
		String name = request.getParameter("search");
		// System.out.println("input data " + name);

		if (name != null)
			titles.addAll(Arrays.asList(name.split("###")));

		JSONParser parser = new JSONParser();
		Map<Integer, Integer> mapped_kw = new HashMap<Integer, Integer>();

		try {

			if (inputReadFile) {
				System.out.println("Reading JSON file from Java program");
				FileReader fileReader = new FileReader(getServletContext().getRealPath("input.json"));
				JSONObject json = (JSONObject) parser.parse(fileReader);
				JSONArray myJsonArray = (JSONArray) json.get("videos");

				for (int i = 0; i < myJsonArray.size(); i++) {
					JSONObject snippet = (JSONObject) ((JSONObject) myJsonArray.get(i)).get("snippet");
					titles.add(snippet.get("title").toString());
				}

				inputReadFile = false;
			}

			System.out.println("Titles count: " + titles.size());

			MaxentTagger tagger = new MaxentTagger(getServletContext().getRealPath("taggers/left3words-wsj-0-18.tagger"));

			Class.forName("org.sqlite.JDBC");
			Connection dbConn = DriverManager.getConnection("jdbc:sqlite:" + dbpath);
			// System.out.println("Opened DB successfully");
			Statement stmt = dbConn.createStatement();
			ResultSet resultSet = stmt.executeQuery("SELECT Category_ID ID, Category_NAME Name, Keywords FROM Dictionary");

			while (resultSet.next()) {
				for (int i = 0; i < titles.size(); i++) {
					// Tag words
					String taggedTitle = tagger.tagTokenizedString(titles.get(i));
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
			// Edit for UI
			Map<String, String> listData = new TreeMap<String, String>();
			List<String> imgList = new ArrayList<String>();
			while (resultSet.next()) {
				imgList.add(resultSet.getString("path"));
				// System.out.println(resultSet.getString("path"));
			}

			if(imgList.size() == 3){
				listData.put("one", imgList.get(0));
				listData.put("two", imgList.get(1));
				listData.put("three", imgList.get(2));
			}
			else {
				listData.put("one","img\\education1.jpg");
				listData.put("two", "img\\sports1.jpg");
				listData.put("three", "img\\movie1.jpg");
			}

			String foodMap = new Gson().toJson(listData);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(foodMap);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
