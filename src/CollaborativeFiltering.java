import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class CollaborativeFiltering
{
	public static void getCriteria()
	{
		int[] candidateTravellerCriteria=(Traveler.AllTravelers.get(Traveler.traveler_counter-1).getPreferences());

		Map<String,Integer> cityToRank=Traveler.AllTravelers.stream().collect(Collectors.toMap(Traveler::getVisit, i->innerDot(i.getPreferences(),candidateTravellerCriteria), (visit1, visit2) -> visit1));

		String cityRecommendation = Collections.max(cityToRank.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();

		if (!Traveler.AllTravelers.get(Traveler.traveler_counter-1).getVisit().equals(cityRecommendation))
		{
			UI recUI = new UI();

			String[] s = cityRecommendation.split(",");
			recUI.alertWindowBasic("Recommendation", "Given your choices, we also recommend you to check out: "+s[0]+" in "+s[1], "Continue");
		}
	}

	private static int innerDot(int[] currentTraveller, int[] candidateTraveller)
	{
		int sum=0;
		for (int i=0; i<currentTraveller.length;i++) sum+=currentTraveller[i]*candidateTraveller[i];
		return sum;
	}
}




