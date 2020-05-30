/*
import java.sql.*;


public class mysql {
	static Connection db_con_obj = null;
	static PreparedStatement db_prep_obj = null;
	
	private static void makeJDBCConnection() {
		 
		try {
			//We check that the DB Driver is available in our project.
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Congrats - Seems your MySQL JDBC Driver Registered!"); 
		} catch (ClassNotFoundException e) {
			System.out.println("Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
			e.printStackTrace();
			return;
		}

 //jdbc:mysql://127.0.0.2:3306/city_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
		try {
			// DriverManager: The basic service for managing a set of JDBC drivers.	 //We connect to a DBMS.
			//Using the DriverManager, we can have many connections to different DBMS
			db_con_obj = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cities_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","it21870", "it21870");
			if (db_con_obj != null) {
				System.out.println("Connection Successful!");
			} else {
				System.out.println("Failed to make connection!");
			}
		} catch (SQLException e) {
			System.out.println("MySQL Connection Failed!");
			e.printStackTrace();
			return;
		}
		finally {
			System.out.println("Insert Completed.");
		}
	}

	private static void getDataFromDB() {
		 
		try {
			// A simple MySQL Select Query 
			String getQueryStatement = "SELECT * FROM city_table;";
			
			// We make a statement to the connected DBMS. We pass to the statement a query.
			db_prep_obj = db_con_obj.prepareStatement(getQueryStatement); 
 
			// Execute the Query, and get a java ResultSet
			ResultSet rs = db_prep_obj.executeQuery();
			
			// Let's iterate through the java ResultSet
			while (rs.next()) {
				City k = new City();
				k.setName(rs.getString("City_name"));
				k.setCountry(rs.getString("Country"));
			    k.setLat(rs.getDouble("LAT"));
			    k.setLon(rs.getDouble("Lot"));
			    k.setMuseums(rs.getInt("Museum"));
			    k.setCafes(rs.getInt("Cafes"));
			    k.setRestaurants(rs.getInt("Restaurant"));
			    k.setBars(rs.getInt("Bars"));
			    k.setBeaches(rs.getInt("Beaches"));
			    k.setMonuments(rs.getInt("Monuments"));
			    
				//Float address = rs.getFloat("VALUE");//  .getString("Address");
				//int employeeCount = rs.getInt("PSS_DIAGNOSIS_DATE_ID");
				//int website = rs.getInt("PSS_SYMPTOMS_ONSETDATE_ID");
 
				// Simply Print the results
			    System.out.printf("%s\t%s\t\t%s\t%s\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n","City name","Country", "Latitude", "Longitude","Museums","Cafes","Restaurant","Bars","Beaches","Monuments");
			    //System.out.printf("%s\t%s\t\t%f\t%f\t%d\t\t%d\t\t\t%d\t\t%d\t\t%d\t\t%d\n",k.city_name ,country , lat, lot,museums ,cafes,restaurant,bars,beaches,monuments);
			}
 
		} catch (
 
		SQLException e) {
			e.printStackTrace();
		}
      
		
	}
	 public static void post() throws Exception{
	       
	        try{
	        	String query = "INSERT into city_table (city_name ,country , lat, lot,museums ,cafes,restaurant,bars,beaches,monuments) " + "VALUES (?,?,?,?,?,?,?,?,?,?)";
	        	db_prep_obj = db_con_obj.prepareStatement(query);
	        	db_prep_obj = db_con_obj.prepareStatement("INSERT INTO city_table (city_name ,country , lat, lot,museums ,cafes,restaurant,bars,beaches,monuments) VALUES (?,?,?,?,?,?,?,?,?,?)");
	        	for (int i = 0; i <= City.allCities.size() - 1; i++){
	        	  db_prep_obj.setString (i,City.allCities.toString());
	        	  db_prep_obj.executeUpdate();
	        	}
	        } catch(Exception e){System.out.println(e);}
	        finally {
	            System.out.println("Insert Completed.");
	        }
	    }

	public static void main(String [] args) throws Exception
	{

		makeJDBCConnection();
		post();
		//getDataFromDB();
	}
}*/
