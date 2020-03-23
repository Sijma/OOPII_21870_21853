import com.fasterxml.jackson.databind.ObjectMapper;
import weather.OpenWeatherMap;
import wikipedia.MediaWiki;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

/**
 * @author sijma
 *
 */
public class Main
{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException, FileNotFoundException
	{
		// TODO Auto-generated method stub
		City c = new City("Athens", "GR");
		c.setName("Prague");
		c.setWeather("windy");
		c.setLon(0.0);
		c.setLat(0.0);
		c.setMuseums(5);
		c.setCafes(8);
		c.setRestaurants(4);
		c.setBars(9);
		c.setBeaches(0);
		c.setMonuments(0);
		c.toString();
		//OpenData.RetrieveData("athens");
		ObjectMapper mapper = new ObjectMapper();
		OpenWeatherMap weather_obj = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?q=athens&APPID="+OpenData.appid +""), OpenWeatherMap.class);
		System.out.println(weather_obj.getWeather());
		MediaWiki mediaWiki_obj =  mapper.readValue(new URL("https://en.wikipedia.org/w/api.php?action=query&prop=extracts&titles=athens&format=json&formatversion=2"),MediaWiki.class);
		//System.out.println("Athens Wikipedia article: "+mediaWiki_obj.getQuery().getPages().get(0).getExtract());
		String a = mediaWiki_obj.getQuery().getPages().get(0).getExtract();
		System.out.println(a);
	}
}
