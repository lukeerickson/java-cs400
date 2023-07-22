import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataWranglerTests {
  
  AirportLoader loader;
  
  @BeforeEach
  void setUp() throws Exception {
     loader = new AirportLoader();
  }
  @Test
  void test1() throws IOException, ParseException { //tests whether a FileNotFoundException gets thrown when the file is not found
    try {
      List<Airport> test = loader.loadAirports("Airport List");
      fail();
    } catch(FileNotFoundException e) {
      assertTrue(true);
    }
  }
  @Test
  void test2() throws IOException, ParseException { //tests whether a FileNotFoundException is not thrown when the file is not found
    try {
      List<Airport> test = loader.loadAirports("Airport List.json");
      assertTrue(true);
    } catch(FileNotFoundException e) {
      fail();
    }
  }
  @Test
  void test3() throws FileNotFoundException, IOException, ParseException { //checks if the first data point is correct
    List<Airport> test = loader.loadAirports("Airport List.json");
    assertTrue(test.get(0).getCityName().equals("New York"));
    assertTrue(test.get(0).getStateAbbreviation().equals("NY"));
    assertTrue(test.get(0).getTimeZone().equals("America/New_York"));
    assertTrue(test.get(0).getLatitude().equals(((Double)40.6943).floatValue()));
    assertTrue(test.get(0).getLongitude().equals(((Double)(-73.9249)).floatValue()));
  }
  @Test
  void test4() throws FileNotFoundException, IOException, ParseException { //checks if the second data point is correct
    List<Airport> test = loader.loadAirports("Airport List.json");
    assertTrue(test.get(1).getCityName().equals("Los Angeles"));
    assertTrue(test.get(1).getStateAbbreviation().equals("CA"));
    assertTrue(test.get(1).getTimeZone().equals("America/Los_Angeles"));
    assertTrue(test.get(1).getLatitude().equals(((Double) 34.1139).floatValue()));
    assertTrue(test.get(1).getLongitude().equals(((Double) (-118.4068)).floatValue()));
  }
  @Test
  void test5() throws FileNotFoundException, IOException, ParseException { //checks if the third data point is correct
    List<Airport> test = loader.loadAirports("Airport List.json");
    assertTrue(test.get(2).getCityName().equals("Chicago"));
    assertTrue(test.get(2).getStateAbbreviation().equals("IL"));
    assertTrue(test.get(2).getTimeZone().equals("America/Chicago"));
    assertTrue(test.get(2).getLatitude().equals(((Double) 41.8373).floatValue()));
    assertTrue(test.get(2).getLongitude().equals(((Double) (-87.6862)).floatValue()));
  }
}
