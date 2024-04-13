package commands;

import commandhelper.Command;
import servercommunication.Message;
import elements.Movie;
import elements.MovieCollection;

import java.io.Serial;
import java.io.Serializable;

public class RemoveLower implements Command, Serializable {
    @Override
    public Message execute(MovieCollection movieCollection, Object object) {
        if (movieCollection.removeLower((Movie) object)) {
            return new Message(
                    false,
                    "All lower movies successfully deleted"
            );
        } else {
            return new Message(
                    true,
                    "No lower movies found"
            );
        }
    }

    @Serial
    private static final long serialVersionUID = -8205772871816724728L;
}
