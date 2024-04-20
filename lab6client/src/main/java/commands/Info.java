package commands;

import commandhelper.Command;
import commandhelper.Message;
import elements.MovieCollection;

import java.io.Serial;
import java.io.Serializable;

public class Info implements Command, Serializable {
    @Override
    public Message execute(MovieCollection movieCollection, Object object) {
        return new Message(false,
                "Number of movies: " + movieCollection.getNumberOfMovies() +
                "\nDate of creation: " + movieCollection.getCreationDate() +
                "\nCollection type: " + movieCollection.getCollectionType()
        );
    }

    @Serial
    private static final long serialVersionUID = -5276054172780947277L;
}
