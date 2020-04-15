import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

public class Traveler
{
	private String Name,Visit;
	public static String methods[] = {"Museums", "Cafes", "Restaurants", "Bars", "Beaches", "Monuments"};
	private int Age;
	private ArrayList<City> CitiesArray;
	private Boolean[] preferences;
	public static int traveler_counter;
	static Scanner scan = new Scanner(System.in);

	public Traveler()
	{
		Name = "";
		Age = 0;
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

	public String getVisit() { return Visit; }

	public void setVisit(String visit) { Visit = visit; }

	/**
	 * Asks for input until a valid int is given
	 * @return Valid int from input
	 */
	public static int intScan()
	{
		Scanner scanint = new Scanner(System.in);
		int result;
		while (!scanint.hasNextInt())
		{
			scanint.next();
			System.out.println("Invalid input. Please try again");
		}
		result = scanint.nextInt();
		return result;
	}

	/**
	 * Asks for city names until 'end' is given
	 * @return ArrayList of all valid cities given by user
	 * @throws IOException
	 */
	public ArrayList<String> InputCities() throws IOException
	{
		ArrayList<String> GivenCities = new ArrayList<String>(); //Making arraylist for all given cities.
		String city = "";
		boolean valid;
		int i;
		System.out.println("Add a few potential cities in [City],[CountryInitials] format. Enter 'end' when done.\n");
		while (!city.equals("end"))
		{
			city = scan.nextLine();
			city = city.replaceAll("\\s+", "%20");
			city = city.toLowerCase();
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
					GivenCities.add(city); //Add to arraylist if all valid
					System.out.println(City.FixCityName(city) + " added\nType 'end' if you want to end here.\n");
				}
			}
			else if (GivenCities.size() == 0)
			{
				System.out.println("Please add at least 1 city.\n");
				city = "";
			}
		}
		return GivenCities;
	}

	/**
	 * change boolean array preferences depending on input
	 * @throws IOException
	 */
	public void PreferenceTags() throws IOException
	{
		int i = 0;
		int choice;
		boolean valid;
		System.out.print("what would you like to visit the most? Enter the corresponding numbers one by one or 7 to finalize input: \n1)Museums\n2)Cafe\n3)Restaurants\n4)Bars\n5)Beaches\n6)Monuments\n7)End\n");
		while (i <= 6)
		{
			choice = intScan();
			if (choice > 0 && choice < 7) //Input is within choices
			{
				if (preferences[choice - 1]) System.out.println("Already registered, please choose another\n");
				else
					{
						preferences[choice - 1] = true;
						System.out.print(methods[choice - 1] +" added");
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

	/**
	 *
	 * @param c City that we want to compare with traveler
	 * @return Similarity between traveler's variables and city in
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 */
	public double Similarity(City c) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException
	{
		Method method;
		int i;
		int temp;
		int DistinctWords;
		double result;
		int similars = 0;
		for (i = 0; i <= 5; i++)
		{
			method = c.getClass().getMethod("get"+ methods[i]);
			temp = (int) method.invoke(c);
			if (temp > 0 && preferences[i])
			{
				similars++;
			}
		}
		DistinctWords = c.CountDistinctWords(c.getWikiInfo());
		result = (similars *1.0)/ DistinctWords;
		result = result * 1000;
		return result;
	}

	/**
	 *
	 * @param CitiesArray List of cities to compare
	 * @return City obj with highest similarity result
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	public City CompareCities(ArrayList<City> CitiesArray) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException
	{
		int IndexOfHighest = -1;
		double temp;
		double max = 0.0;
		int i;
		for (i = 0; i <= CitiesArray.size() - 1; i++)
		{
			temp = Similarity(CitiesArray.get(i));
			if (temp > max)
			{
				max = temp;
				IndexOfHighest = i;
			}
		}
		return CitiesArray.get(IndexOfHighest);
	}

	/**
	 *
	 * @param CitiesArray List of cities to compare
	 * @param rain Boolean whether or not to consider cities with Rain weather
	 * @return
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	public City CompareCities(ArrayList<City> CitiesArray, boolean rain) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException
	{
		int i;
		String temp;
		if (rain)
		{
			for (i = 0; i <= CitiesArray.size() - 1; i++)
			{
				temp = CitiesArray.get(i).getWeather();
				if (temp.equalsIgnoreCase("Rain"))
				{
					CitiesArray.remove(i);
					i--;
				}
			}
		}
		return CompareCities(CitiesArray);
	}

	/**
	 * Prints a menu for the user and asks for input until a valid option is given
	 * @return Valid option from the menu to 'execute'
	 */
	public static int MenuOption()
	{
		int choice = 0;
		System.out.print("Hello customer and welcome to our tourist agency app.\nThis app has been created to help you find the ideal travel destination ");
		System.out.println("So let's start by answering some questions.\n");
		System.out.print("What's the purpose of your travel?\n1)I just want to travel the world.\n2)I am going for business trip.\n3)I just want to relax as a tourist.\n4)Exit Program\n");
		while (choice < 1 || choice > 4)
		{
			choice = intScan();
		}
		return choice;
	}

	/**
	 * Prints name and country of given city obj
	 * @param c City obj to get info from
	 */
	public void PrintCityInfo(City c)
	{
		System.out.print("Your Suggested city is: " + c.getName() + " in " + c.getCountry() + "!\n\n");
		Visit = c.getName();
	}

	public static boolean travelerExists(Traveler t)
	{
		int i;
		for (i=0;i<=Main.AllTravelers.size()-2;i++)
		{
			if (Main.AllTravelers.get(i).equals(t)) return true;
		}
		return false;
	}
}