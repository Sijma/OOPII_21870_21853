import com.fasterxml.jackson.databind.ObjectMapper;
import weather.OpenWeatherMap;
import wikipedia.MediaWiki;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;


public class City implements Serializable
{
	public static ArrayList<City> allCities = new ArrayList<>();

	private int Museums,Cafes,Restaurants,Bars,Beaches,Monuments;
	private double lat,lon;
	private String weather;
	private String name, cityName;
	private String country;
	private String WikiInfo;
	public static String SearchTags[] = {"museum", "café", "restaurant", "bar", "beach", "monument"};
	public static final String appid = "867714155299b363bca7483aecae6458";

	public City()
	{
		name = "";
		country = "";
		Museums = 0;
		Cafes = 0;
		Restaurants = 0;
		Bars = 0;
		Beaches = 0;
		Monuments = 0;
		lat = 0;
		lon = 0;
		weather = "";
		WikiInfo = "";
		cityName = name + ","+ country;
	}

	public City(String name, String country)
	{
		this.name = name;
		this.country = country;
		Museums = 0;
		Cafes = 0;
		Restaurants = 0;
		Bars = 0;
		Beaches = 0;
		Monuments = 0;
		lat = 0;
		lon = 0;
		weather = "";
		WikiInfo = "";
		cityName = name + ","+ country;
	}

	public City(String name, int museums, int cafes, int restaurants , int bars, int beaches, int monuments, double lat, double lon, String weather) {
		this.name = name;
		this.Museums = museums;
		this.Cafes = cafes;
		this.Restaurants = restaurants;
		this.Bars = bars;
		this.Beaches = beaches;
		this.Monuments = monuments;
		this.lat=lat;
		this.lon = lon;
		this.weather = weather;
	}

	public String getWikiInfo()
	{
		return WikiInfo;
	}

	public void setWikiInfo(String info) { this.WikiInfo = info; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getBeaches() { return Beaches; }

    public void setBeaches(int Beaches) { this.Beaches = Beaches; }

    public int getMonuments() { return Monuments; }

    public void setMonuments(int Monuments) { this.Monuments = Monuments; }

	public String getCountry() { return country; }

	public int getMuseums() {
		return Museums;
	}

	public void setMuseums(int museums) {
		Museums = museums;
	}

	public int getCafes() {
		return Cafes;
	}

	public void setCafes(int cafes) {
		Cafes = cafes;
	}

	public int getRestaurants() {
		return Restaurants;
	}

	public void setRestaurants(int restaurants) {
		Restaurants = restaurants;
	}

	public int getBars() {
		return Bars;
	}

	public void setBars(int bars) {
		Bars = bars;
	}

	public double getLat() { return lat; }

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getCityName() { return cityName; }

	public void setCityName(String cityName) { this.cityName = cityName; }

	public void setCountry(String country) { this.country = country; }


/**
	 *
	 * @param CityName String to check validity of as existing city
	 * @return boolean depending on valid or not
	 */

/**
	 *
	 * @param CityName Name of city to look for wiki info
	 * @return String of full wiki result of given city
	 * @throws IOException
	 */

	public String cityWikiInfo(String CityName) throws IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		MediaWiki mediaWiki_obj =  mapper.readValue(new URL("https://en.wikipedia.org/w/api.php?action=query&prop=extracts&titles="+CityName+"&format=json&formatversion=2"),MediaWiki.class);

		String info = mediaWiki_obj.getQuery().getPages().get(0).getExtract();
		info = info.replaceAll("\\<.*?\\>", "");
		info = info.toLowerCase();
		return info;
	}

/**
	 *
	 * @param str String to count distinct words in
	 * @return amount of distinct words
	 */

	public int CountDistinctWords(String str)
	{
		String s[]=str.split(" ");
		ArrayList<String> list=new ArrayList<String>();
		for (int i = 1; i < s.length; i++)
		{
			if (!(list.contains(s[i])))
			{
				list.add(s[i]);
			}
		}
		return 	list.size();
	}

/**
	 *
	 * @param str string to count total words in
	 * @return amount of total words
	 */

	public static int CountTotalWords(String str) {

		String s[]=str.split(" ");
		return 	s.length;
	}


/**
	 * sets given city's coordinates
	 * @param c City obj to find and set coordinates for
	 * @throws IOException
	 */

	public void CityCoords(City c) throws IOException
	{

		ObjectMapper mapper = new ObjectMapper();
		OpenWeatherMap weather_obj = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?q="+c.name+","+c.country+"&APPID="+appid+""), OpenWeatherMap.class);
		c.setLat(weather_obj.getCoord().getLat());
		c.setLon(weather_obj.getCoord().getLon());
	}


/**
	 * Sets weather for instance
	 * @throws IOException
	 */

	private void CityWeather(City c) throws IOException
	{

		ObjectMapper mapper = new ObjectMapper();
		OpenWeatherMap weather_obj = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?q="+c.cityName+"&APPID="+appid+""), OpenWeatherMap.class);
		c.setWeather(weather_obj.getWeather().get(0).getMain());
	}


/**
	 *
	 * @param str String to search in
	 * @param Pattern Pattern to look for in str
	 * @return Amount of times Pattern appears in str
	 */

	public int CountWordResults (String str, String Pattern)
	{
		int index = str.indexOf(Pattern);
		int count = 0;
		while (index != -1)
		{
			count++;
			str = str.substring(index + 1);
			index = str.indexOf(Pattern);
		}
		return count;
	}

	@Override
	public boolean equals(Object o)
	{
		City c = (City) o;
		if (c.name.equalsIgnoreCase(this.name))
		{
			return true;
		}
		return false;
	}

	public City buildCity(String cityName) throws IOException, InterruptedException
	{
		String[] C = cityName.split(",");
		City tempCity = new City(C[0], C[1]);
		int index = CityExists(tempCity);

		if (index == -1)
		{
			Sthr thread = new Sthr(tempCity);
			thread.start();
			CityCoords(tempCity);
			CityWeather(tempCity);

			while (thread.isAlive())
			{
				System.out.println("Still going");
				thread.join();
			}
		allCities.add(tempCity);
		}
		else
		{
			return allCities.get(index);
		}
		return tempCity;
	}

	public int CityExists(City c)
	{
		int i;
		for (i=0;i<=allCities.size()-1;i++)
		{
			if (allCities.get(i).equals(c)) return i;
		}
		return -1;
	}
}
