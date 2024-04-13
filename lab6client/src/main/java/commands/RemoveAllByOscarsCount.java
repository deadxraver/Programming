package commands;

import commandhelper.Command;
import servercommunication.Message;
import elements.MovieCollection;

import java.io.Serial;
import java.io.Serializable;

public class RemoveAllByOscarsCount implements Command, Serializable {
    @Override
    public Message execute(MovieCollection movieCollection, Object object) {
        if (movieCollection.removeByOscar((Integer) object)) {
            return new Message(
                    false,
                    "Movies with such oscars count have been successfully deleted"
            );
        } else {
            return new Message(
                    true,
                    "No matches found"
            );
        }
    }

    @Serial
    private static final long serialVersionUID = -3928714350596327042L;
}
