package commandsPack;

import elementsPack.Movie;
import mainPack.MovieCollection;

public class Show extends Command{
    public Show(MovieCollection collection) {
        this.collection = collection;
    }
    @Override
    public void execute() {
        for (Movie movie : collection.getCollection()) {
            System.out.println(movie);
        }
    }
}
