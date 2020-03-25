import com.fasterxml.jackson.databind.ObjectMapper;
import weather.OpenWeatherMap;
import wikipedia.MediaWiki;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * @author lampr
 *
 */
public class City
{
	private int Museums,Cafes,Restaurants,Bars,Beaches,Monuments;
	private double lat,lon;
	private String weather;
	private String name;
	private String country;
	private String info;

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
		info = "";
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

	public String getInfo()
	{
		return info;
	}

	public void setInfo(String info)
	{
		this.info = info;
	}

    public String getName()
	{
        return name;
    }

    public void setName(String name)
	{
        this.name = name;
    }

    public int getBeaches()
	{
        return Beaches;
    }

    public void setBeaches(int Beaches)
	{
        this.Beaches = Beaches;
    }

    public int getMonuments()
	{
        return Monuments;
    }

    public void setMonuments(int Monuments)
	{
        this.Monuments = Monuments;
    }

	public String getCountry()
	{
		return country;
	}
	/**
	 * @return the museums
	 */
	public int getMuseums() {
		return Museums;
	}
	/**
	 * @param museums the museums to set
	 */
	public void setMuseums(int museums) {
		Museums = museums;
	}
	/**
	 * @return the cafes
	 */
	public int getCafes() {
		return Cafes;
	}
	/**
	 * @param cafes the cafes to set
	 */
	public void setCafes(int cafes) {
		Cafes = cafes;
	}
	/**
	 * @return the restaurants
	 */
	public int getRestaurants() {
		return Restaurants;
	}
	/**
	 * @param restaurants the restaurants to set
	 */
	public void setRestaurants(int restaurants) {
		Restaurants = restaurants;
	}
	/**
	 * @return the bars
	 */
	public int getBars() {
		return Bars;
	}
	/**
	 * @param bars the bars to set
	 */
	public void setBars(int bars) {
		Bars = bars;
	}
	/**
	 * @return the lan
	 */
	public double getLat() {
		return lat;
	}
	/**
	 * @param lat the lan to set
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}
	/**
	 * @return the lon
	 */
	public double getLon() {
		return lon;
	}
	/**
	 * @param lon the lon to set
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}
	/**
	 * @return the weather
	 */
	public String getWeather() {
		return weather;
	}
	/**
	 * @param weather the weather to set
	 */
	public void setWeather(String weather) {
		this.weather = weather;
	}

	public static boolean ValidCity(String CityName) throws IOException
	{
		//ArrayIndexOutOfBoundsException
		String C[] = CityName.split(",");
		if (C.length != 2)
		{
			System.out.println("Wrong Format. Please type in: [City],[CountryInitials], without the brackets.\n");
			return false;
		}
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			OpenWeatherMap weather_obj = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?q="+CityName+"&APPID="+OpenData.appid+""), OpenWeatherMap.class);
			weather_obj.getMain();
		}
		catch (Exception FileNotFoundException)
		{
			System.out.println("City doesn't exist.\n");
			return false;
		}
		return true;
	}

	public String CityWikiInfo(String CityName) throws IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		MediaWiki mediaWiki_obj =  mapper.readValue(new URL("https://en.wikipedia.org/w/api.php?action=query&prop=extracts&titles="+CityName+"&format=json&formatversion=2"),MediaWiki.class);
		String info = mediaWiki_obj.getQuery().getPages().get(0).getExtract();
		info = info.replaceAll("\\<.*?\\>", "");
		return info;
	}

	public int CountDistinctWords(String CityInfo)
	{
		String s[]=CityInfo.split(" ");
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

	public int CountWordResults (String CityInfo, String Pattern)
	{
		int index = CityInfo.indexOf(Pattern);
		int count = 0;
		while (index != -1)
		{
			count++;
			CityInfo = CityInfo.substring(index + 1);
			index = CityInfo.indexOf(Pattern);
		}
		return count;
	}

	public void FillCityInfo (City c)
	{

	}
}

