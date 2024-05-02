package commands;

import commandhelper.Command;
import commandhelper.Message;
import elements.Movie;
import elements.MovieCollection;

import java.io.Serial;
import java.io.Serializable;

public class Add implements Command, Serializable {
    @Override
    public Message execute(MovieCollection movieCollection, Object object) {
        movieCollection.addMovie((Movie) object);
        return new Message(
                false,
                null
        );
    }
    @Serial
    private static final long serialVersionUID = -9203005047180764168L;
}
