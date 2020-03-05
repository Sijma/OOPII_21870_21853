import com.fasterxml.jackson.databind.ObjectMapper;
import weather.OpenWeatherMap;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

/**
 * @author sijma
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException, FileNotFoundException
	{
		// TODO Auto-generated method stub
		City c = new City();
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
	}
}
