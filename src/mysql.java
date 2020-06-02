import java.sql.*;


public class mysql {
	static Connection db_con_obj = null;
	static PreparedStatement db_prep_obj = null;
	
	public static void makeJDBCConnection() {
		try
        {
			//We check that the DB Driver is available in our project.
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Congrats - Seems your MySQL JDBC Driver Registered!"); 
		}
		catch (ClassNotFoundException e)
        {
			System.out.println("Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
			e.printStackTrace();
			return;
		}
		try
        {
			// DriverManager: The basic service for managing a set of JDBC drivers.	 //We connect to a DBMS.
			//Using the DriverManager, we can have many connections to different DBMS
			db_con_obj = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cities_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","it21870", "it21870");
			if (db_con_obj != null) {
				System.out.println("Connection Successful!");
			} else {
				System.out.println("Failed to make connection!");
			}
		}
		catch (SQLException e)
        {
			System.out.println("MySQL Connection Failed!");
			e.printStackTrace();
			return;
		}
	}


	public static void getDataFromDB() {
		 
		try
        {
			String getQueryStatement = "SELECT * FROM city_table;";
			db_prep_obj = db_con_obj.prepareStatement(getQueryStatement);
			ResultSet rs = db_prep_obj.executeQuery();

			while (rs.next())
            {
                City.allCities.add(new City(rs.getString("City_name"), rs.getString("Country"), rs.getDouble("LAT"), rs.getDouble("Lot"), rs.getInt("Museum"), rs.getInt("Cafes"), rs.getInt("Restaurant"), rs.getInt("Bars"), rs.getInt("Beaches"), rs.getInt("Monuments"), rs.getString("Weather"), rs.getInt("TotalWords"), rs.getInt("DistinctWords")));
            }
		}
		catch(SQLException e)
        {
            e.printStackTrace();
        }
      
		
	}

	 public static void post()
     {
         try
         {
             String query = "INSERT into city_table (city_name,country,lat,lot,museum,cafes,restaurant,bars,beaches,monuments,weather,totalwords,distinctwords) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
             db_prep_obj = db_con_obj.prepareStatement(query);

             int i;
             for (i = City.allCities.size() - City.newCities; i < City.allCities.size(); i++)
             {
                 City temp = City.allCities.get(i);

                 db_prep_obj.setString (1,temp.getName());
                 db_prep_obj.setString (2,temp.getCountry());
                 db_prep_obj.setDouble (3,temp.getLat());
                 db_prep_obj.setDouble (4,temp.getLon());
                 db_prep_obj.setInt (5,temp.getMuseums());
                 db_prep_obj.setInt (6,temp.getCafes());
                 db_prep_obj.setInt (7,temp.getRestaurants());
                 db_prep_obj.setInt (8,temp.getBars());
                 db_prep_obj.setInt (9,temp.getBeaches());
                 db_prep_obj.setInt (10,temp.getMonuments());
                 db_prep_obj.setString (11,temp.getWeather());
                 db_prep_obj.setInt (12,temp.getTotalWords());
                 db_prep_obj.setInt (13,temp.getDistinctWords());

                 db_prep_obj.executeUpdate();
             }
             City.newCities = 0;
         }
         catch(Exception e){System.out.println(e);}
     }
}
