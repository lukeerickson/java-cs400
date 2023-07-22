// --== CS400 Project One File Header ==--
// Name: Utkarsh Sehgal
// CSL Username: sehgal
// Email: usehgal@wisc.edu
// Lecture #: 004
// Notes to Grader: <any optional extra notes to your grader>
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShowSearcherBackend<KeyType, ValueType> implements IShowSearcherBackend {
  protected HashTableSortedSets titleTable;
  protected HashTableSortedSets yearTable;
  private boolean N;
  private boolean H;
  private boolean P;
  private boolean D;
  private int size;

  public ShowSearcherBackend() {
    titleTable = new HashTableSortedSets();
    yearTable = new HashTableSortedSets();
    N = true;
    H = true;
    P = true;
    D = true;
  }

  /**
   * @param show = the show we are adding to the database
   */
  @Override
  public void addShow(IShow show) {
    String[] titlewords = show.getTitle().replaceAll("\\p{Punct}", " ").toLowerCase().split(" ");
    for (int i = 0; i < titlewords.length; i++) {
      titleTable.add(titlewords[i], show);
    }
    yearTable.add(show.getYear(), show);
    size++;
  }

  /**
   * get the size of the database
   * 
   * @return = int that represents the size of the database
   */
  @Override
  public int getNumberOfShows() {
    return size;
  }

  /**
   * 
   * sets a certain provider's boolean value
   * 
   * @param provider = the provider we are setting the boolean value of
   */
  @Override
  public void setProviderFilter(String provider, boolean filter) {
    if (provider.charAt(0) == 'N') {
      N = filter;
    } else if (provider.charAt(0) == 'H') {
      H = filter;
    } else if (provider.charAt(0) == 'P') {
      P = filter;
    } else {
      D = filter;
    }
  }

  /**
   * 
   * gets a certain provider's boolean value
   * 
   * @param provider = the provider we are toggling
   * @return = boolean that represents if the provider is available
   */
  @Override
  public boolean getProviderFilter(String provider) {
    if (provider.charAt(0) == 'N') {
      return N;
    } else if (provider.charAt(0) == 'H') {
      return H;
    } else if (provider.charAt(0) == 'P') {
      return P;
    } else {
      return D;
    }
  }

  /**
   * 
   * toggles a certain provider
   * 
   * @param provider = the provider we are toggling
   */
  @Override
  public void toggleProviderFilter(String provider) {
    if (provider.charAt(0) == 'N') {
      N = !N;
    } else if (provider.charAt(0) == 'H') {
      H = !H;
    } else if (provider.charAt(0) == 'P') {
      P = !P;
    } else {
      D = !D;
    }

  }

  /**
   * search the database by the word in the title of the show
   * 
   * @param word = the word we are checking for
   * @return = list of shows sorted by rating that contain the word
   */
  @Override
  public List<IShow> searchByTitleWord(String word) {
    ArrayList<IShow> shows = new ArrayList<IShow>();
    for (LinkedList<NodeValue<KeyType, List<ValueType>>> a : titleTable.nodes) {
      if (a != null) {
        for (NodeValue<KeyType, List<ValueType>> b : a) {

          if (b.getKey().equals(word.toLowerCase()))
            for (ValueType c : b.getValue()) {
              //if (checkProviders((IShow) c)) {
                if (shows.size() == 0) {
                  shows.add((IShow) c);
                } else {
                  for (int i = 0; i < shows.size(); i++) {
                    if (shows.get(i).compareTo((IShow) c) < 0) {
                      shows.add(i, (IShow) c);
                      break;
                    }
                  }
                }
              }
            //}
        }
      }
    }
    return shows;
  }

  /**
   * search the database by the year of the show
   * 
   * @param year = the year we are checking for
   * @return = list of shows sorted by rating that are from the year
   */
  @Override
  public List<IShow> searchByYear(int year) {
    ArrayList<IShow> shows = new ArrayList<IShow>();
    for (LinkedList<NodeValue<KeyType, List<ValueType>>> a : yearTable.nodes) {
      if (a != null) {
        for (NodeValue<KeyType, List<ValueType>> b : a) {
          for (ValueType c : b.getValue()) {
            if (b.getKey().equals(year) & checkProviders((IShow) c)) {
              if (shows.size() == 0) {
                shows.add((IShow) c);
              } else {
                for (int i = 0; i < shows.size(); i++) {
                  if (shows.get(i).compareTo((IShow) c) < 0) {
                    shows.add(i, (IShow) c);
                    break;
                  }
                }
              }
            }
          }
        }
      }
    }
    return shows;
  }

  /**
   * helper to check providers
   * 
   * @param show = the show we are checking
   * @return = boolean that represents whether the providers are available for the show
   */
  private boolean checkProviders(IShow show) {
    if (N) {
      if (show.isAvailableOn("Netflix"))
        return true;
    } else if (H) {
      if (show.isAvailableOn("Hulu"))
        return true;
    } else if (P) {
      if (show.isAvailableOn("Amazon Prime"))
        return true;
    } else if (D) {
      if (show.isAvailableOn("Disney+"))
        return true;
    }
    return false;
  }

}
