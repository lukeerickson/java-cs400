import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AirportLoader implements IAirportLoader {

  @Override
  public List<Airport> loadAirports(String filepath) throws FileNotFoundException, IOException, ParseException {
  //parsing file "JSONExample.json"
  Object obj = new JSONParser().parse(new FileReader(filepath));
  
  // typecasting obj to JSONObject
  JSONArray jo = (JSONArray) obj;

  ArrayList<Airport> airports = new ArrayList<>();
  for(Object temp: jo) {
    JSONObject j = (JSONObject) temp;
    String cityName = (String) j.get("city");
    String stateAbbreviation = (String) j.get("state_id");
    String timeZone = (String) j.get("timezone");
    Float latitude = ((Double) j.get("lat")).floatValue();
    Float longitude = ((Double) j.get("lng")).floatValue();
    airports.add(new Airport(cityName, stateAbbreviation, timeZone, latitude, longitude));
  }
  return airports;
  }
  
}
