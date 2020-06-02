import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Sthr extends Thread
{
	private City tempCity;

	public Sthr (City tempCity)
	{
		this.tempCity = tempCity;
	}

	public void run()
	{
		try
		{
			tempCity.setWikiInfo(tempCity.cityWikiInfo(tempCity.getName()));
			tempCity.CountTotalWords(tempCity.getWikiInfo());
			tempCity.CountDistinctWords(tempCity.getWikiInfo());

			int i;
			int count;
			String text;
			for (i = 0; i <= 5; i++)
			{
				text = Traveler.methods[i];
				Method method = tempCity.getClass().getMethod("set" + text, int.class);
				count = tempCity.CountWordResults(tempCity.getWikiInfo(), City.SearchTags[i]);
				method.invoke(tempCity, count);
			}
		}
		catch (IOException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
	}
}