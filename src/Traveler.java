import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

public class Traveler implements Comparable<Traveler>, Serializable
{
	public static ArrayList<Traveler> AllTravelers = new ArrayList<>();

	private String Name,Visit,sourceCity, sourceCountry;
	public static String[] methods = {"Museums", "Cafes", "Restaurants", "Bars", "Beaches", "Monuments"};
	private int Age;
	private ArrayList<City> travelerCitiesArray;
	private int[] preferences;
	public static int traveler_counter;
	static Scanner scan = new Scanner(System.in);

	public Traveler()
	{
		Name = "";
		Age = 0;
		sourceCity = "";
		sourceCountry = "";
		travelerCitiesArray = new ArrayList<>();
		preferences = new int[6];
	    traveler_counter++;
	    Visit = "";
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

	public ArrayList<City> getTravelerCitiesArray()
	{
		return travelerCitiesArray;
	}

	public void setTravelerCitiesArray(ArrayList<City> travelerCitiesArray)
	{
		this.travelerCitiesArray = travelerCitiesArray;
	}

	public int getTraveler_counter() {
		return traveler_counter;
	}

	public void setTraveler_counter(int traveler_counter) {
		Traveler.traveler_counter++;
	}

	public int[] getPreferences() { return preferences; }

	public void setPreferences(int[] preferences) { this.preferences = preferences; }

	public String getSourceCity()
	{
		return sourceCity;
	}

	public void setSourceCity(String sourceCity)
	{
		this.sourceCity = sourceCity;
	}

	public String getSourceCountry()
	{
		return sourceCountry;
	}

	public void setSourceCountry(String sourceCountry)
	{
		this.sourceCountry = sourceCountry;
	}

	public String getVisit() { return Visit; }

	public void setVisit(String visit) { Visit = visit; }

/**
	 *
	 * @param c City that we want to compare with traveler
	 * @return Similarity between traveler's variables and city in
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 */

	public double Similarity(City c) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, IOException
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
			if (temp > 0 && preferences[i] == 1)
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

	public City compareCities(ArrayList<City> CitiesArray) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, IOException
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

	public City CompareCities(ArrayList<City> CitiesArray, boolean rain) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, IOException
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
		return compareCities(CitiesArray);
	}

	public boolean equals(Object o)
	{
		Traveler t = (Traveler) o;
		if (t.Name.equalsIgnoreCase(this.Name))
		{
			return true;
		}
		return false;
	}

	public static boolean travelerExists(Traveler t)
	{
		int i;
		for (i=0;i<=AllTravelers.size()-2;i++)
		{
			if (AllTravelers.get(i).equals(t)) return true;
		}
		return false;
	}

	@Override
	public int compareTo(Traveler t)
	{
		return this.Age - t.Age;
	}

	public static void saveTravelers() throws IOException
	{
		FileOutputStream fos = new FileOutputStream("resources/TravelersFile");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(AllTravelers);
		oos.close();
	}

	public static void loadTravelers() throws IOException, ClassNotFoundException
	{
		File temp = new File("resources/TravelersFile");
		boolean exists = temp.exists();
		if (exists)
		{
			FileInputStream fis = new FileInputStream("resources/TravelersFile");
			ObjectInputStream ois = new ObjectInputStream(fis);
			AllTravelers = (ArrayList<Traveler>) ois.readObject();
			ois.close();
			traveler_counter = AllTravelers.size();
		}
	}

	public void buildTraveler(String name, int age, String sourceCity, String sourceCountry, Boolean[] selections)
	{
		int i;
		int[] intSelections = new int[6];
		for (i = 0; i<=5;i++)
		{
			intSelections[i] = (selections[i]) ? 1 : 0;
		}

		this.Name = name;
		this.Age = age;
		this.sourceCity = sourceCity;
		this.sourceCountry = sourceCountry;
		this.preferences = intSelections.clone();
	}

	public void addCities(ArrayList<String> cityNamesList) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException
	{
		City cityBuilder = new City();

		int i;
		for (i = 0; i <= cityNamesList.size()-1; i++)
		{
			travelerCitiesArray.add(cityBuilder.buildCity(cityNamesList.get(i)));
		}
	}
}
