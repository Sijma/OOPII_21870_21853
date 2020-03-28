import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import weather.OpenWeatherMap;
import java.lang.Math;

public class Business extends Traveler
{
    private double lat,lon;
    public static int MaxDistance = 20036;
    
    private double distance(double lat1, double lon1, double lat2, double lon2) 
    {
        lat1 = lat;
        lon1 = lon;
	    if ((lat1 == lat2) && (lon1 == lon2))
            {
                return 0;
            }
        else
            {
                double theta = lon1 - lon2;
                double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
                dist = Math.acos(dist);
                dist = Math.toDegrees(dist);
                dist = dist * 60 * 1.1515 * 1.609344;
                return (dist);
            }
    }
    public static double log2(double x)
    {
        return (Math.log(x) / Math.log(2));
    }

    @Override
    public double Similarity(City c)
    {
        return log2((2-distance(lat,lon,c.getLat(),c.getLon()))/MaxDistance);
    }
}
