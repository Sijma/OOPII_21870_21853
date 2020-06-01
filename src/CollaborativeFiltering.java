
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CollaborativeFiltering {

  public static void getCriteria()
  {

  	int[] candidateTravellerCriteria=(Traveler.AllTravelers.get(Traveler.traveler_counter-1).getPreferences());

  	Map <String,Integer> cityToRank=Traveler.AllTravelers.stream().collect(Collectors.toMap(Traveler::getVisit, i->innerDot(i.getPreferences(),candidateTravellerCriteria)));
  	cityToRank.forEach((k,v)->System.out.println("city:"+k+" rank: "+v));

  	Optional<RecommendedCity> recommendedCity=
			Traveler.AllTravelers.stream().map(i-> new RecommendedCity(i.getVisit(),innerDot(i.getPreferences(),candidateTravellerCriteria))).max(Comparator.comparingInt(RecommendedCity::getRank));

  	System.out.println("The Recommended City:"+recommendedCity.get().getCity());

}
	private static int innerDot(int[] currentTraveller, int[] candidateTraveller) {
		int sum=0;
		for (int i=0; i<currentTraveller.length;i++) sum+=currentTraveller[i]*candidateTraveller[i];
		return sum;
	}
	
}




