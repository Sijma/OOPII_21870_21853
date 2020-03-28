
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Tourist extends Traveler
{
	@Override
	public double Similarity(City c) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException
	{
		java.lang.reflect.Method method;
		int i,matching=0;
		int temp,total=0;
		int similars = 0;

		for (i = 0; i <= 5; i++)
		{
			Method m = c.getClass().getMethod("get"+methods[i]);
			temp = (int) m.invoke(c);
			total = total + temp;
			if (temp >= 0 && getPreferences()[i])
			{
				similars++;
				matching = matching + temp;
			}
		}
		return (similars*matching)/total;
  	}
}