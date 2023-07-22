
public class Show implements IShow{
  
  private String title;
  private int year;
  private int rating;
  private String providers;
  
  public Show(String title, int year, int rating, String providers) {
    this.title = title;
    this.year = year;
    this.rating = rating;
    this.providers = providers;
  }

  @Override
  public int compareTo(IShow o) {
    return rating - o.getRating();
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public int getYear() {
    return year;
  }

  @Override
  public int getRating() {
    return rating;
  }

  @Override
  public boolean isAvailableOn(String provider) {
    return providers.contains(provider);
  }
  @Override
  public String toString() {
    return title + " " + year + " " + rating;
  }
}
