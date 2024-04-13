package commands;

import commandhelper.Command;
import servercommunication.Message;
import elements.MovieCollection;
import exceptions.NoSuchMovieException;

import java.io.Serial;
import java.io.Serializable;

public class RemoveById implements Command, Serializable {

    @Override
    public Message execute(MovieCollection movieCollection, Object object) {
        try {
            movieCollection.removeMovie((Long) object);
            return new Message(
                    false,
                    null
            );
        } catch (NoSuchMovieException e) {
            return new Message(
                    true,
                    e.getMessage()
            );
        }
    }

    @Serial
    private static final long serialVersionUID = 6947888240733986366L;
}
