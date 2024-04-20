package parsers;

import elements.Movie;
import elements.MovieCollection;

public class CollectionParser {
    /**
     * Parses the given MovieCollection, removing duplicate movies and invalid entries.
     *
     * @param  movieCollection   the original MovieCollection to be parsed
     * @return                  the parsed MovieCollection with duplicates and invalid entries removed
     */
    public static MovieCollection parse(MovieCollection movieCollection) {
        MovieCollection parsedMovieCollection = new MovieCollection(movieCollection.getCreationDate());
        for (Object o : movieCollection.getCollection()) {
            Movie movie = (Movie) o;
            for (Object o1 : movieCollection.getCollection()) {
                Movie movie1 = (Movie) o1;
                if (movie.getId() == movie1.getId() && !movie.equals(movie1)) {
                    movieCollection.removeMovie(movie);
                }
            }
        }
        for (Object o : movieCollection.getCollection()) {
            Movie movie = (Movie) o;
            boolean toBeRemoved = false;
            if (movie.getId() <= 0) {
                toBeRemoved = true;
            }
            if (movie.getOscarsCount() <= 0) {
                toBeRemoved = true;
            }
            if (movie.getOperator() != null) {
                if (movie.getOperator().getName().replaceAll(" ", "").isEmpty() || movie.getOperator().getName() == null) {
                    movie.setOperator(null);
                }
            }
            if (movie.getCoordinates() == null) {
                toBeRemoved = true;
            }
            else {
                if (movie.getCoordinates().getX() == null) {
                    toBeRemoved = true;
                }
                if (movie.getCoordinates().getY() == null || movie.getCoordinates().getY() > 274) {
                    toBeRemoved = true;
                }
            }
            if (movie.getCreationDate() == null) {
                toBeRemoved = true;
            }
            if (movie.getGenre() == null) {
                toBeRemoved = true;
            }
            if (!toBeRemoved) {
                parsedMovieCollection.addMovie(movie);
            }
        }
        return parsedMovieCollection;
    }
}
