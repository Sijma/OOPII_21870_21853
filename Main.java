import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
	public static ArrayList<Traveler> AllTravelers = new ArrayList<Traveler>();
	public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		int choice = 0;
		int index;
		int age;
		ArrayList<String> TempCityNames = new ArrayList<String>();
		ArrayList<City> TempCityObjectsList = new ArrayList<City>();
		City BestCity;
		Scanner scan = new Scanner(System.in);
		while (choice != 4)
		{
			choice = Traveler.MenuOption();
			TempCityNames.clear();
			TempCityObjectsList.clear();
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
			else
			{
				break;
			}
			if (choice !=4)
			{
				index = Traveler.traveler_counter - 1;
				System.out.printf("What is your name?\n");
				AllTravelers.get(index).setName(scan.next());
				System.out.printf("How old are you?\n");
				age = Traveler.intScan();
				while (age <= 0)
				{
					System.out.printf("Invalid input. Please try again\n");
					age = Traveler.intScan();
				}
				int i;
				AllTravelers.get(index).setAge(age);
				TempCityNames = AllTravelers.get(index).InputCities();
				for (i=0;i<=TempCityNames.size()-1;i++)
				{
					String C[] = TempCityNames.get(i).split(",");
					TempCityObjectsList.add(new City(C[0],C[1]));
					TempCityObjectsList.get(i).FillCityInfo(TempCityObjectsList.get(i));
				}
				AllTravelers.get(index).setCitiesArray(TempCityObjectsList);
				AllTravelers.get(index).PreferenceTags();
				TempCityObjectsList = AllTravelers.get(index).getCitiesArray();
				BestCity = AllTravelers.get(index).CompareCities(TempCityObjectsList);
				AllTravelers.get(index).PrintCityInfo(BestCity);
			}
		}
	}
}