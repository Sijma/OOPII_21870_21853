import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;
import weather.OpenWeatherMap;
import java.net.URL;

public class Traveler
{
	private String Name;
	//public Boolean chosen[] = {false, false, false, false, false, false}; //array of available and user choice
	public static String methods[] = {"Museums", "Cafes", "Restaurants", "Bars", "Beaches", "Monuments"};
	private int Age, current_lat, current_lon;
	private ArrayList<City> CitiesArray;
	private Boolean[] preferences;
	public static int traveler_counter;
	static Scanner scan = new Scanner(System.in);

	public Traveler()
	{
		Name = "";
		Age = 0;
		current_lat = 0;
		current_lon = 0;
		CitiesArray = new ArrayList<City>();
		preferences = new Boolean[] {false,false,false,false,false,false};
	    traveler_counter++;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public int getCurrent_lat() {
		return current_lat;
	}

	public void setCurrent_lat(int current_lat) {
		this.current_lat = current_lat;
	}

	public int getCurrent_lon() {
		return current_lon;
	}

	public void setCurrent_lon(int current_lon) {
		this.current_lon = current_lon;
	}

	public ArrayList<City> getCitiesArray()
	{
		return CitiesArray;
	}

	public void setCitiesArray(ArrayList<City> citiesArray)
	{
		CitiesArray = citiesArray;
	}

	public int getTraveler_counter() {
		return traveler_counter;
	}

	public void setTraveler_counter(int traveler_counter) {
		Traveler.traveler_counter++;
	}

	public Boolean[] getPreferences()
	{
		return preferences;
	}

	public void setPreferences(Boolean[] preferences)
	{
		this.preferences = preferences;
	}

	public static int intScan()
	{
		while (!scan.hasNextInt())
		{
			scan.next();
			System.out.println("Invalid input. Please try again");
		}
		return scan.nextInt();
	}

	public ArrayList<String> InputCities() throws IOException
	{
		ArrayList<String> GivenCities = new ArrayList<String>(); //Making arraylist for all given cities.
		String city = "";
		boolean valid;
		int i;
		System.out.println("Add a few potential cities in [City],[CountryInitials] format. Enter 'end' when done.\n");
		while (!city.equals("end"))
		{
			city = scan.next();
			if (!city.equals("end"))
			{
				valid = City.ValidCity(city); //Check if city exists
				i = 0;
				while (i <= GivenCities.size() - 1 && valid)
				{
					if (city.equalsIgnoreCase(GivenCities.get(i))) //Check if input has already been registered before.
					{
						System.out.println("This city has already been registered.\n");
						valid = false;
						break;
					}
					i++;
				}
				if (valid)
				{
					city = City.FixCityName(city);
					GivenCities.add(city);
					System.out.println(city + " added"); //Add to arraylist if all valid
				}
			}
			else if (GivenCities.size() == 0)
			{
				System.out.println("Please add at least 1 city.");
				city = "";
			}
		}
		return GivenCities;
	}

	public void PreferenceTags()
	{
		int i = 0;
		int choice;
		boolean valid;
		System.out.printf("what would you like to visit the most? Enter the corresponding numbers one by one or 7 to finalize input: \n1)Museums\n2)Cafe\n3)Restaurants\n4)Bars\n5)Beaches\n6)Monuments\n7)End\n");
		while (i <= 6)
		{
			choice = intScan();
			if (choice > 0 && choice < 7) //Input is within choices
			{
				if (preferences[choice - 1]) System.out.println("Already registered, please choose another\n");
				else
					{
						preferences[choice - 1] = true;
						i++;
					}
			}
			else if (choice == 7)
			{
				if (i == 0) System.out.println("You haven't chosen anything. Choose at least 1 of the options.\n");
				else break;
			} else System.out.println("Invalid input. Please try again\n");
		}
	}

	public double Similarity(City c) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException
	{
		Method method;
		int i;
		int temp;
		int similars = 0;
		for (i = 0; i <= 5; i++)
		{
			Method m = c.getClass().getMethod("get"+ methods[i]);
			temp = (int) m.invoke(c);
			if (temp >= 0 && preferences[i])
			{
				similars++;
			}
		}
		return similars / c.CountDistinctWords(c.getWikiInfo());
	}

	public City CompareCities(ArrayList<City> CitiesArray) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException
	{
		City highest = null;
		double max = -1;
		int i;
		for (i = 0; i <= CitiesArray.size() - 1; i++)
		{
			if (Similarity(CitiesArray.get(i)) > max)
			{
				max = Similarity(CitiesArray.get(i));
				highest = CitiesArray.get(i);
			}
		}
		return highest;
	}

	public City CompareCities(ArrayList<City> CitiesArray, boolean weather) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException //NEEDS REWORK!
	{
		int i;
		if (weather)
		{
			ObjectMapper mapper = new ObjectMapper();
			for (i = 0; i <= CitiesArray.size() - 1; i++)
			{
			}
		}
		return CompareCities(CitiesArray);
	}

	public static int MenuOption()
	{
		int choice = 0;
		System.out.printf("Hello customer and welcome to our tourist agency app.\nThis app has been created to help you find the ideal travel destination ");
		System.out.println("So let's start by answering some questions.\n");
		System.out.printf("What's the purpose of your travel?\n1)I just want to travel the world.\n2)I am going for business trip.\n3)I just want to relax as a tourist.\n4)Exit Program\n");
		while (choice < 1 || choice > 4)
		{
			choice = intScan();
		}
		return choice;
	}
}