
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Tourist extends Traveler
{
	@Override
	public double Similarity(City c) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException
	{
		System.out.printf("similar0\n");
		int i,matching=0;
		int temp;
		double result;
		int similars = 0;
		for (i = 0; i <= 5; i++)
		{
			System.out.printf("similar1\n");
			Method m = c.getClass().getMethod("get"+methods[i]);
			temp = (int) m.invoke(c);
			if (temp > 0 && getPreferences()[i])
			{
				System.out.printf(methods[i]);
				System.out.printf("similar2\n");
				similars++;
				System.out.print("similars: "+similars);
				matching = matching + temp;
				System.out.print("matching: "+matching);
			}
		}
		System.out.printf("similar3\n");
		result = (similars*matching*1.0)/City.CountTotalWords(c.getWikiInfo());
		System.out.printf("Result: "+result);
		return result;
  	}
}