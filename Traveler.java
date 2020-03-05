import java.util.ArrayList;
import java.util.Scanner;  
public class Traveler
{
	String Name;
 	int Age,current_lat,current_lon;
 	private static int traveler_counter;
	/**
	 * @param name
	 * @param age
	 * @param current_lat
	 * @param current_lon
	 */
	public Traveler(String name, int age, int current_lat, int current_lon)
	{

		this.Name = name;
		this.Age = age;
		this.current_lat = current_lat;
		this.current_lon = current_lon;
		traveler_counter++;

	}
	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return Age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		Age = age;
	}
	/**
	 * @return the current_lat
	 */
	public int getCurrent_lat() {
		return current_lat;
	}
	/**
	 * @param current_lat the current_lat to set
	 */
	public void setCurrent_lat(int current_lat) {
		this.current_lat = current_lat;
	}
	/**
	 * @return the current_lon
	 */
	public int getCurrent_lon() {
		return current_lon;
	}
	/**
	 * @param current_lon the current_lon to set
	 */
	public void setCurrent_lon(int current_lon) {
		this.current_lon = current_lon;
	}

	 public void preference()
	 {
		 String pr_array[] = {"Museums","Cafe","Restaurants","Bars","Beaches","Monuments"};
		 String chosen[] = {null,null,null,null,null,null}; //array of available and user choices
		 int i = 0;
		 int j;
		 int choice = 0;
		 boolean valid;
		 Scanner input = new Scanner(System.in); //User input and validity checks
		 System.out.printf("what would you like to visit the most? Enter the corresponding numbers one by one or 7 to finalize input: \n1)Museums\n2)Cafe\n3)Restaurants\n4)Bars\n5)Beaches\n6)Monuments\n7)End\n");
			while (i <= 6 )
			{
				while (!input.hasNextInt()) //Input is int
				{
					input.next();
					System.out.println("Invalid input. Please try again\n");
				}
				choice = input.nextInt();
				if (choice > 0 && choice < 7) //Input is within choices
				{
					valid = true;
					for (j=0;j<=i-1;j++)
					{
						if (chosen[j] == pr_array[choice-1]) //Input has already been registered
						{
							System.out.println("Already registered, please choose another\n");
							valid = false;
							break;
						}
					}
					if (valid) //If all valid, add to array of user choices.
					{
						chosen[i] = pr_array[choice-1];
						System.out.println(chosen[i]+" added\n");
						i++;
					}
				}
				else if (choice == 7)
				{
					if (i == 0) System.out.println("You haven't chosen anything. Choose at least 1 of the options.\n");
					else break;
				}
				else System.out.println("Invalid input. Please try again\n");
			}
			int chosenAmount = i-1; //Amount of chosen keywords
			System.out.println("Add a few potential cities. Enter 'end' when done.");
			ArrayList<String> pCities = new ArrayList<String>(); //Making arraylist for potential cities.
			String potentialCity = "";
			while (!potentialCity.equals("end"))
			{
				potentialCity = input.next();
				if (!potentialCity.equals("end"))
				{
					valid = City.ValidCity(potentialCity); //Check if city exists
					i = 0;
					while (i <= pCities.size()-1 && valid)
					{
						if (potentialCity.equalsIgnoreCase(pCities.get(i))) //Check if input has already been registered before.
						{
							System.out.println("City Already exists.");
							valid = false;
							break;
						}
						i++;
					}
					if (valid)
					{
						pCities.add(potentialCity);
						System.out.println(potentialCity+" added"); //Add to arraylist if all valid
					}
				}
				else if (pCities.size() == 0)
				{
					System.out.println("Please add at least 1 city.");
					potentialCity = "";
				}
			}
			input.close();
	 }
	public int getTraveler_counter() {
		return traveler_counter;
	}
	public void setTraveler_counter(int traveler_counter) {
		Traveler.traveler_counter++;
	}

	public double Similarity (City C)
	{
		return 0.0;
	}

	public City CompareCities(ArrayList<City> CitiesArray)
	{
		City highest = null;
		double max = -1;
		int i;
		for (i=0;i<=CitiesArray.size()-1;i++)
		{
			if (Similarity(CitiesArray.get(i)) > max)
			{
				max = Similarity(CitiesArray.get(i));
				highest = CitiesArray.get(i);
			}
		}
		return highest;
	}

	public City CompareCities(ArrayList<City> CitiesArray, boolean weather)
	{
		if (weather)
		{
			ObjectMapper mapper = new ObjectMapper();
			for (i=0;i<=CitiesArray.size()-1;i++)
			{
				OpenWeatherMap weather_obj = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?q="+CitiesArray.get(i).getName();+"&APPID="+OpenData.appid+""), OpenWeatherMap.class);
				if(weather_obj.getWeather().getMain().eq("Rain"))
				{
					CitiesArray.remove(i);
				}
			}
		}
		City highest = null;
		double max = -1;
		int i;
		for (i=0;i<=CitiesArray.size()-1;i++)
		{
			if (Similarity(CitiesArray.get(i)) > max)
			{
				max = Similarity(CitiesArray.get(i));
				highest = CitiesArray.get(i);
			}
		}
		return highest;
	}


	/*&public static int CountDistinctWords(City c1) {
		c1.toString();
		//String s[]=str.split(" ");
		ArrayList<String> list=new ArrayList<String>();

		for (int i = 1; i < s.length; i++) {
			if (!(list.contains(s[i]))) {
				list.add(s[i]);
			}
		}
		return 	list.size();
	}
	*/
}