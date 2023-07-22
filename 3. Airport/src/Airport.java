/**
 * Defines the airports or nodes of the graph
 */
public class Airport {
    // Airports Instance Variables
    private final String cityName;
    private final String stateAbbreviation;
    private final String timeZone;
    private final Float latitude;
    private final Float longitude;

    /**
     * Initialises the final fields for a new Airport object
     *
     * @param cityName          - City name where airport is located
     * @param stateAbbreviation - State abbreviations where airport is located
     * @param timeZone          - Time zone of airport
     * @param latitude          - latitude
     * @param longitude         - longitude
     */
    public Airport(String cityName, String stateAbbreviation, String timeZone, Float latitude,
                   Float longitude) {
        this.cityName = cityName;
        this.stateAbbreviation = stateAbbreviation;
        this.timeZone = timeZone;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * returns the cityName
     *
     * @return cityName
     */
    public String getCityName() {
        return this.cityName;
    }

    /**
     * returns the stateAbbreviation
     *
     * @return stateAbbreviation
     */
    public String getStateAbbreviation() {
        return this.stateAbbreviation;
    }

    /**
     * returns the timeZone
     *
     * @return timeZone
     */
    public String getTimeZone() {
        return this.timeZone;
    }

    /**
     * returns the Latitude
     *
     * @return Latitude
     */
    public Float getLatitude() {
        return this.latitude;
    }

    /**
     * returns the longitude
     *
     * @return longitude
     */
    public Float getLongitude() {
        return this.longitude;
    }

    /**
     * Returns a string representation of an Airport object
     *
     * @return a string representation of an Airport
     */
    @Override
    public String toString() {

        return ("City: " + cityName + "\nState: " + stateAbbreviation + "\nTime Zone: " + timeZone);
    }
}