import java.io.IOException;

public class Business extends Traveler
{
    public static int MaxDistance = 20036;

    /**
     *
     * @param latc Latitude of city to measure distance
     * @param lonc Longitude of city to measure distance
     * @return distance between given city's and instance coordinates
     */

    private double distance(double latc, double lonc) throws IOException
    {
        double lat,lon;
        City source = new City(getSourceCity(),getSourceCountry());
        source.CityCoords(source);

        lat = source.getLat();
        lon = source.getLon();

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
     *
     * @param c City that we want to compare with traveler
     * @return Similarity depending on distance
     */

    @Override
    public double Similarity(City c) throws IOException
    {
        double temp;
        temp = log2(2-distance(c.getLat(),c.getLon())/MaxDistance);
        return temp;
    }
}

