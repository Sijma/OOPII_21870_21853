import com.fasterxml.jackson.databind.ObjectMapper;
import weather.OpenWeatherMap;
import wikipedia.MediaWiki;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.net.URL;

/**City description and weather information using OpenData with Jackson JSON processor.
* @since 29-2-2020
* @version 1.0
* @author John Violos */
public class OpenData
{
	public static final String appid = "867714155299b363bca7483aecae6458";
	/**Retrieves weather information, geotag (lan, lon) and a Wikipedia article for a given city.
	* @param city The Wikipedia article and OpenWeatherMap city. */

	 public static void RetrieveData(String city) throws  IOException, FileNotFoundException
	 {
		 ObjectMapper mapper = new ObjectMapper();
		 OpenWeatherMap weather_obj = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?q="+city+"&APPID="+appid+""), OpenWeatherMap.class);
		 System.out.println(city+" temperature: " + (weather_obj.getMain()).getTemp());
		 System.out.println(city+" lat: " + weather_obj.getCoord().getLat()+" lon: " + weather_obj.getCoord().getLon());
		 MediaWiki mediaWiki_obj =  mapper.readValue(new URL("https://en.wikipedia.org/w/api.php?action=query&prop=extracts&titles="+city+"&format=json&formatversion=2"),MediaWiki.class);
		 System.out.println(city+" Wikipedia article: "+mediaWiki_obj.getQuery().getPages().get(0).getExtract());
	}

}