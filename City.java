


/**
 * @author lampr
 *
 */
public class City {
	int Museums,Cafes,Restaurants,Bars,Beaches,Monuments;
        double lat,lon;
        String weather,name;
	/**
	 * @param museums
	 * @param cafes
	 * @param restaurants
	 * @param bars
         * @param beaches
         * @param monuments
	 * @param lat
	 * @param lon
	 * @param weather
         * @param name
	 */
	public City(String name,int museums, int cafes, int restaurants ,int bars, int beaches,int monuments,double lat, double lon, String weather) {
		this.name = name;
		this.Museums = museums;
		this.Cafes = cafes;
		this.Restaurants = restaurants;
		this.Bars = bars;
                this.Beaches = beaches;
                this.Monuments = monuments;
		this.lat=lat;
		this.lon = lon;
		this.weather = weather;
	}

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBeaches() {
        return Beaches;
    }

    public void setBeaches(int Beaches) {
        this.Beaches = Beaches;
    }

    public int getMonuments() {
        return Monuments;
    }

    public void setMonuments(int Monuments) {
        this.Monuments = Monuments;
    }

    
	/**
	 * @return the museums
	 */
	public int getMuseums() {
		return Museums;
	}
	/**
	 * @param museums the museums to set
	 */
	public void setMuseums(int museums) {
		Museums = museums;
	}
	/**
	 * @return the cafes
	 */
	public int getCafes() {
		return Cafes;
	}
	/**
	 * @param cafes the cafes to set
	 */
	public void setCafes(int cafes) {
		Cafes = cafes;
	}
	/**
	 * @return the restaurants
	 */
	public int getRestaurants() {
		return Restaurants;
	}
	/**
	 * @param restaurants the restaurants to set
	 */
	public void setRestaurants(int restaurants) {
		Restaurants = restaurants;
	}
	/**
	 * @return the bars
	 */
	public int getBars() {
		return Bars;
	}
	/**
	 * @param bars the bars to set
	 */
	public void setBars(int bars) {
		Bars = bars;
	}
	/**
	 * @return the lan
	 */
	public double getLat() {
		return lat;
	}
	/**
	 * @param lat the lan to set
	 */
	public void setLan(double lat) {
		this.lat = lat;
	}
	/**
	 * @return the lon
	 */
	public double getLon() {
		return lon;
	}
	/**
	 * @param lon the lon to set
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}
	/**
	 * @return the weather
	 */
	public String getWeather() {
		return weather;
	}
	/**
	 * @param weather the weather to set
	 */
	public void setWeather(String weather) {
		this.weather = weather;
	}
    
}
