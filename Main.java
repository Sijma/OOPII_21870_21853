import java.util.ArrayList;
import java.util.Scanner;
public class Main
{
	public static ArrayList<Traveler> AllTravelers = new ArrayList<Traveler>();
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		/*City c = new City("Athens", "GR");
		//OpenData.RetrieveData("athens");
		ObjectMapper mapper = new ObjectMapper();
		OpenWeatherMap weather_obj = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?q=athens&APPID="+OpenData.appid +""), OpenWeatherMap.class);
		System.out.println(weather_obj.getWeather());
		MediaWiki mediaWiki_obj =  mapper.readValue(new URL("https://en.wikipedia.org/w/api.php?action=query&prop=extracts&titles=london&format=json&formatversion=2"),MediaWiki.class);
		//System.out.println("Athens Wikipedia article: "+mediaWiki_obj.getQuery().getPages().get(0).getExtract());
		String a = mediaWiki_obj.getQuery().getPages().get(0).getExtract();
		a = a.replaceAll("\\<.*?\\>", "");
		System.out.println(a);
		 */
		int choice = 0;
		int index;
		int age;
		Scanner scan = new Scanner(System.in);
		while (choice != 4)
		{
			choice = Traveler.MenuOption();
			if(choice==1)
			{
				AllTravelers.add(new Traveler());
			}
			else if(choice==2)
			{
				AllTravelers.add(new Business());
			}
			else if(choice==3)
			{
				AllTravelers.add(new Tourist());
			}
			else if (choice!=4)
			{
				System.out.printf("Invalid input. Please try again");
			}

			if (choice !=4)
			{
				index = Traveler.traveler_counter - 1;
				System.out.printf("What is your name?");
				AllTravelers.get(index).setName(scan.next());
				System.out.printf("How old are you?");
				age = Traveler.intScan();
				while (age <= 0)
				{
					System.out.printf("Invalid input. Please try again\n");
					age = Traveler.intScan();
				}
				AllTravelers.get(index).setAge(age);
			}
		}
	}
}