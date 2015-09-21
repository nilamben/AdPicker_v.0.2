package src.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbConnect {

	public static void createDb(String dbPath) {

		try {
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			System.out.println("Opened DB successfully");

			Statement stmt = con.createStatement();
			String query = "CREATE TABLE Dictionary(Category_ID INT PRIMARY KEY NOT NULL, Category_Name TEXT, Keywords TEXT)";
			stmt.executeUpdate(query);

			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (1, 'Film & Animation', 'Frozen,kung fu Panda,tarzen')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (2, 'Autos & Vehicles', 'Tesla Cars,Elantra,Maruti,Nisan')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (10, 'Music','Songs,VCategory_IDeo songs,Coming home,let it go, music')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (15, 'Pets & Animals', 'Labourador,Cat,dog,lion,tiger,parrot')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (17, 'Sports', 'Star,Tensports,F1,Race,Cricket,Tennis,Kabaddi,Sachin,Brett lee')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (18, 'Short Movies','Ahalyaa,one love')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (19, 'Travel & Events','Ejypt,Taj Mahal,Orchestra,concert')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (20, 'Gaming', 'Super mario,rubiks,board,games,vCategory_IDeo')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (21, 'VCategory_IDeoblogging','')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (22, 'People & Blogs','')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (23, 'Comedy','')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (24, 'Entertainment','WWE,serials,House of cards')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (25, 'News & Politics','')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (26, 'Howto & Style','')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (27, 'Education', 'Java,Tutorial,Programming,Python,.Net,Excersizes,Sample,Javascript')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (28, 'Science & Technology','')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (29, 'Nonprofits & Activism','')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (30, 'Movies', 'Movies,Animation,English,Hindi,cartoon')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (31, 'Anime/Animation','')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (32, 'Action/Adventure','')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (33, 'Classics','')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (34, 'Comedy','Comedy, Circus, Mahabali, laughter')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (35, 'Documentary', 'Documentary,body of henry,Indian Army')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (36, 'Drama','')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (37, 'Family','')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (38, 'Foreign','')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (39, 'Horror','')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (40, 'Sci-Fi/Fantasy','')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (41, 'Thriller','')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (42, 'Shorts','')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (43, 'Shows','')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Dictionary(Category_ID, Category_Name, Keywords) VALUES (44, 'Trailers', 'Daddys,home,car,official,trailor,furniture,housing,movie')";
			stmt.executeUpdate(query);

			query = "CREATE TABLE Ads(Keyword VARCHAR(255)," + "ID INT, Category_ID INT, Image_path TEXT,"
					+ "FOREIGN KEY(Category_ID) REFERENCES Dictionary(Category_ID))";
			stmt.executeUpdate(query);

			query = "INSERT INTO Ads(ID, Category_ID, Image_path) values (1, 17, 'img\\sports2.jpg')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Ads(ID, Category_ID, Image_path) values (2, 27, 'img\\education1.jpg')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Ads(ID, Category_ID, Image_path) values (3, 34, 'img\\comedy1.jpg')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Ads(ID, Category_ID, Image_path) values (4, 1, 'img\\animated1.jpg')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Ads(ID, Category_ID, Image_path) values (5, 30, 'img\\horror1.jpg')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Ads(ID, Category_ID, Image_path) values (6, 10, 'img\\music1.jpg')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Ads(ID, Category_ID, Image_path) values (7, 20, 'img\\game1.jpg')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Ads(ID, Category_ID, Image_path) values (8, 44, 'img\\trailers1.jpg')";
			stmt.executeUpdate(query);
			query = "INSERT INTO Ads(ID, Category_ID, Image_path) values (8, 43, 'img\\show1.jpg')";
			stmt.executeUpdate(query);

			stmt.close();
			con.close();

		} catch (Exception ex) {
			System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
			System.exit(0);
		}

		System.out.println("Tables created successfully");
	}
}
