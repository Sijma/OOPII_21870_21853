import com.fasterxml.jackson.databind.ObjectMapper;
import weather.OpenWeatherMap;

import java.net.URL;

public class Validate
{
    public static final String appid = "867714155299b363bca7483aecae6458";

    public boolean isInt(String input)
    {
        try
        {
            int num = Integer.parseInt(input);
        }
        catch (NumberFormatException e)
        {
            return false;
        }
        return true;
    }

    public boolean validName (String input)
    {
        return (input != null) && (!input.equals("")) && (input.matches("^[a-zA-Z]*$"));
    }

    public boolean isPositiveInt(String input)
    {
        try
        {
            int num = Integer.parseInt(input);
            return num>0;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }

    public boolean validCity(String cityName, String countryName)
    {
        if (!validName(cityName) || !validName(countryName))
        {
            return false;
        }

        ObjectMapper mapper = new ObjectMapper();
        try
        {
            OpenWeatherMap weather_obj = mapper.readValue(new URL("https://api.openweathermap.org/data/2.5/weather?q="+cityName+","+countryName+"&APPID="+appid+""), OpenWeatherMap.class);
            String temp = weather_obj.getSys().getCountry();
            if (!temp.equalsIgnoreCase(countryName))
            {
                return false;
            }
        }
        catch (Exception FileNotFoundException)
        {
            return false;
        }

        return true;
    }
}
