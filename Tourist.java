
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author it21853
 */
public class Tourist extends Traveler
{
 
 @Override
  public double Similarity(City c)
  throws InvocationTargetException, IllegalAccessException, NoSuchMethodException
	{
		java.lang.reflect.Method method;
		int i,matching=0;
		int temp,total=0;
		int similars = 0;
                
		for (i = 0; i <= 5; i++)
		{
			Method m = c.getClass().getMethod("get"+ tags[i]);
			temp = (int) m.invoke(c);
                        total = total + temp;
			if (temp >= 0 && chosen[i])
			{
				similars++;
                                matching = matching + temp;
                        }
		}
                return (similars*matching)/total;
  }
}

