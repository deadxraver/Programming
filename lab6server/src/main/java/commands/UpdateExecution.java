package commands;

import commandhelper.Command;
import commandhelper.Message;
import elements.Movie;
import elements.MovieCollection;

import java.io.Serial;
import java.io.Serializable;

public class UpdateExecution implements Command, Serializable {
    @Override
    public Message execute(MovieCollection movieCollection, Object object) {
        movieCollection.addMovie((Movie) object);
        return new Message(
                false,
                "Movie successfully updated"
        );
    }
    @Serial
    private static final long serialVersionUID = 7237025522341671747L;
}
