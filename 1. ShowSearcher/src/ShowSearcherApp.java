import java.util.List;

public class ShowSearcherApp {
    public static void main(String[] args) throws Exception {
        IShowLoader loader = new ShowLoader();
        List<IShow> shows = loader.loadShows("C:\\Users\\lukee\\eclipse-workspace\\400 P1 ShowSearcher\\src\\tv_shows.csv");
        IShowSearcherBackend backend = new ShowSearcherBackend();
        for(IShow show : shows) backend.addShow(show);
        IShowSearcherFrontend frontend = new ShowSearcherFrontend(backend);
        frontend.runCommandLoop();
    }
}