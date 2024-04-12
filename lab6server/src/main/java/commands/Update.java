package commands;

import commandhelper.Command;
import commandhelper.Message;
import elements.MovieCollection;
import exceptions.NoSuchMovieException;

import java.io.Serial;
import java.io.Serializable;

public class Update implements Command, Serializable {
    @Override
    public Message execute(MovieCollection movieCollection, Object object) {
        try {
            movieCollection.getElement((Long) object);
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
    private static final long serialVersionUID = 4755656231949117218L;
}
