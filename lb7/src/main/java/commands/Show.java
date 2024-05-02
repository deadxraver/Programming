package commands;

import commandhelper.Command;
import commandhelper.Message;
import elements.MovieCollection;

import java.io.Serial;
import java.io.Serializable;

public class Show implements Command, Serializable {
    @Override
    public Message execute(MovieCollection movieCollection, Object object) {
        return new Message(
                false,
                movieCollection.toString()
        );
    }

    @Serial
    private static final long serialVersionUID = -2051979921933526308L;
}
