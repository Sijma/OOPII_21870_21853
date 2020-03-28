
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Tourist extends Traveler
{
	@Override
	public double Similarity(City c) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException
	{
		int i,matching=0;
		int temp;
		double result;
		int similars = 0;
		for (i = 0; i <= 5; i++)
		{
			Method m = c.getClass().getMethod("get"+methods[i]);
			temp = (int) m.invoke(c);
			if (temp > 0 && getPreferences()[i])
			{
				similars++;
				matching = matching + temp;
			}
		}
		result = (similars*matching*1.0)/City.CountTotalWords(c.getWikiInfo());
		return result;
  	}
}