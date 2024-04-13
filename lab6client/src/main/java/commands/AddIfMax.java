package commands;

import commandhelper.Command;
import exceptions.EmptyCollectionException;
import servercommunication.Message;
import elements.Movie;
import elements.MovieCollection;

import java.io.Serial;
import java.io.Serializable;

public class AddIfMax implements Command, Serializable {
    @Override
    public Message execute(MovieCollection movieCollection, Object object) {
        try {
            if (((Movie)object).getCoordinates().getLength() >
                    movieCollection.getMax().getCoordinates().getLength()) {
                movieCollection.addMovie((Movie) object);
                return new Message(
                        false,
                        "Movie successfully added"
                );
            }
        } catch (EmptyCollectionException ignored) {
        }
        return new Message(
                true,
                "Movie is not max"
        );
    }

    @Serial
    private static final long serialVersionUID = 2874280002097979299L;
}
