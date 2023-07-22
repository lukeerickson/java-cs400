import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ShowLoader implements IShowLoader {
  public ShowLoader() {

  }

  public List<IShow> loadShows(String filepath) throws FileNotFoundException {
    File showCSV = new File(filepath);
    if (!showCSV.isFile() || !showCSV.exists()) {
      throw new FileNotFoundException("Your CSV file does not exist");
    }
    List<IShow> shows = new ArrayList<IShow>();
    List<String> titles = new ArrayList<String>();
    List<String> years = new ArrayList<String>();
    List<String> ratings = new ArrayList<String>();
    List<String> platforms = new ArrayList<String>();
    String outerPlatforms = "";
    int title = 2;
    int year = 3;
    int rating = 6;
    int netflix = 7;
    int hulu = 8;
    int prime = 9;
    int disney = 10;
    Scanner csvScanner = new Scanner(showCSV);
    csvScanner.useDelimiter("\"");
    boolean titleWComma = false;
    String tempTitle = "";
    while (csvScanner.hasNext()) {
      String curr = csvScanner.next();
      String[] splitString = curr.split(",");
      if (splitString.length < 10) {
        titleWComma = true;
        tempTitle = curr;
      }
      if (splitString.length > 10) {
        for (int i = 0; i < splitString.length; i++) {
          if (titleWComma) {
            title = 0;
            year = 1;
            rating = 4;
            netflix = 5;
            hulu = 6;
            prime = 7;
            disney = 8;
            titles.add(tempTitle);
            years.add(splitString[year]);
            ratings.add(splitString[rating].split("/")[0]);
            outerPlatforms = "";
            if (splitString[netflix].equals("1")) {
              outerPlatforms = outerPlatforms + "Netflix, ";
            }
            if (splitString[hulu].equals("1")) {
              outerPlatforms = outerPlatforms + "Hulu, ";
            }
            if (splitString[prime].equals("1")) {
              outerPlatforms = outerPlatforms + "Amazon Prime, ";
            }
            if (splitString[disney].equals("1")) {
              outerPlatforms = outerPlatforms + "Disney+, ";
            }
            platforms.add(outerPlatforms);
            titleWComma = false;
          }
          if (i > 10 && !titleWComma) {
            outerPlatforms = "";
            if (i % 11 == title) {
              titles.add(splitString[i]);
            }
            if (i % 11 == year) {
              years.add(splitString[i]);
            }
            if (i % 11 == rating) {
              ratings.add(splitString[i].split("/")[0]);
            }
            if (i % 11 == netflix && splitString[i].equals("1")) {
              outerPlatforms = outerPlatforms + "Netflix, ";
            }
            if (i % 11 == hulu && splitString[i].equals("1")) {
              outerPlatforms = outerPlatforms + "Hulu, ";
            }
            if (i % 11 == prime && splitString[i].equals("1")) {
              outerPlatforms = outerPlatforms + "Amazon Prime, ";
            }
            if (i % 11 == disney && splitString[i].equals("1")) {
              outerPlatforms = outerPlatforms + "Disney+, ";
            }
            if (i % 11 == 10) {
              platforms.add(outerPlatforms);
            }
          }
        }
      }
    }
    for (int i = 0; i < platforms.size() - 1; i++) {
       Show show = new Show(titles.get(i), Integer.valueOf(years.get(i)),
       Integer.valueOf(ratings.get(i)), platforms.get(i));
       shows.add(show);
    }

    return shows;
  }


}
