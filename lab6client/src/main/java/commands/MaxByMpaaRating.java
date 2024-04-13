package commands;

import commandhelper.Command;
import servercommunication.Message;
import elements.MovieCollection;
import exceptions.EmptyCollectionException;

import java.io.Serial;
import java.io.Serializable;

public class MaxByMpaaRating implements Command, Serializable {
    @Override
    public Message execute(MovieCollection movieCollection, Object object) {
        try {
            return new Message(
                    false,
                    movieCollection.getMpaaMax().toString()
            );
        } catch (EmptyCollectionException e) {
            return new Message(
                    true,
                    e.getMessage()
            );
        }
    }

    @Serial
    private static final long serialVersionUID = -1737272390454927855L;
}
