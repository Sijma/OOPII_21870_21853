import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Giveaway
{
    public static final String GIVEAWAY_CITY_NAME = "london";
    public static final String GIVEAWAY_COUNTRY_NAME = "gb";

    public static int getIndexOfLargest(double[] array)
    {
        if (array == null || array.length == 0) return -1; // null or empty

        int largest = 0;
        for (int i = 1; i < array.length; i++)
        {
            if (array[i] > array[largest]) largest = i;
        }
        return largest; // position of the first largest found
    }

    public static String goldenTicket(ArrayList<Traveler> AllTravelers) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, IOException, InterruptedException
    {
        City c = new City(GIVEAWAY_CITY_NAME, GIVEAWAY_COUNTRY_NAME);
        c = c.buildCity(c.getCityName());
        int i;
        int maxpos;
        double[] sim = new double[Traveler.traveler_counter];
        for (i = 0; i <= Traveler.traveler_counter - 1; i++)
        {
            sim[i] = AllTravelers.get(i).Similarity(c);
        }
        maxpos = getIndexOfLargest(sim);
        return AllTravelers.get(maxpos).getName();
    }
}
