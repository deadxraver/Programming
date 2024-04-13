package commands;

import commandhelper.Command;
import servercommunication.Message;
import elements.MovieCollection;
import exceptions.EmptyCollectionException;
import exceptions.NoSuchMovieException;

import java.io.Serial;
import java.io.Serializable;

public class RemoveHead implements Command, Serializable {
    @Override
    public Message execute(MovieCollection movieCollection, Object object) {
        try {
            movieCollection.removeMovie(movieCollection.getMax().getId());
        } catch (EmptyCollectionException e) {
            return new Message(
                    true,
                    e.getMessage()
            );
        } catch (NoSuchMovieException ignored) {
        }
        return new Message(
                false,
                "Movie successfully deleted"
        );
    }

    @Serial
    private static final long serialVersionUID = -925527377588774830L;
}
