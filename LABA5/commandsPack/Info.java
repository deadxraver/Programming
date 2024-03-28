package commandsPack;

import elementsPack.Movie;
import mainPack.MovieCollection;

public class Info extends Command{
    public Info(MovieCollection collection) {
        this.collection = collection;
    }
    @Override
    public void execute() {
        int n = 0;
        System.out.println("Collection type: " + Movie.class +
                "\nDate of initialization: " + collection.getDateOfCreation() +
                "\nNumber of elements stored: " + collection.getNumberOfElements() +
                '\n');
    }
}
