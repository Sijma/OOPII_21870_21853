import com.fasterxml.jackson.databind.ObjectMapper;
import weather.OpenWeatherMap;
import java.io.IOException;
import java.lang.Math;
import java.net.URL;
import java.util.Scanner;

public class Business extends Traveler
{
    private double lat,lon;
    public static int MaxDistance = 20036;

    public double getLat() { return lat; }

    public void setLat(double lat) { this.lat = lat; }

    public double getLon() { return lon; }

    public void setLon(double lon) { this.lon = lon; }

    /**
     *
     * @param latc Latitude of city to measure distance
     * @param lonc Longitude of city to measure distance
     * @return distance between given city's and instance coordinates
     */
    private double distance(double latc, double lonc)
    {
	    if ((lat == latc) && (lon == lonc))
            {
                return 0;
            }
        else
            {
                double theta = lon - lonc;
                double dist = Math.sin(Math.toRadians(lat)) * Math.sin(Math.toRadians(latc)) + Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(latc)) * Math.cos(Math.toRadians(theta));
                dist = Math.acos(dist);
                dist = Math.toDegrees(dist);
                dist = dist * 60 * 1.1515 * 1.609344;
                return (dist);
            }
    }

    /**
     *
     * @param x number to calculate log base 2 off
     * @return result of log base 2 of x
     */
    public static double log2(double x)
    {
        return (Math.log(x) / Math.log(2));
    }

    /**
     * Asks user's current city and sets instance latitude and longitude from given city
     * @throws IOException
     */
    public void PreferenceTags() throws IOException
    {
        String CityName = "";
        Scanner scan = new Scanner(System.in);
        System.out.println("Add the city you are currently in, in [City],[CountryInitials] format.\n");
        boolean valid = false;
        while (!valid)
        {
            CityName = scan.next();
            valid = City.ValidCity(CityName);
        }
        ObjectMapper mapper = new ObjectMapper();
        OpenWeatherMap weather_obj = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?q="+CityName+"&APPID="+City.appid+""), OpenWeatherMap.class);
        lat = weather_obj.getCoord().getLat();
        lon = weather_obj.getCoord().getLon();
    }

    /**
     *
     * @param c City that we want to compare with traveler
     * @return Similarity depending on distance
     */
    @Override
    public double Similarity(City c)
    {
        double temp;
        temp = log2(2-distance(c.getLat(),c.getLon())/MaxDistance);
        return temp;
    }
}
