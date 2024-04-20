package commands;

import commandhelper.Command;
import commandhelper.Message;
import elements.Movie;
import elements.MovieCollection;
import exceptions.NoSuchMovieException;

import java.io.Serial;
import java.io.Serializable;

public class Update implements Command, Serializable {
    @Override
    public Message execute(MovieCollection movieCollection, Object object) {
        Movie movie = (Movie) object;
        try {
            movieCollection.getElement(movie.getId());
            return new Message(
                    true,
                    "This id is already taken"
            );
        } catch (NoSuchMovieException e) {
            movieCollection.addMovie(movie);
            return new Message(
                    false,
                    "Movie successfully updated"
            );
        }
    }

    @Serial
    private static final long serialVersionUID = 4755656231949117218L;
}
