package commands;

import commandhelper.Command;
import servercommunication.Message;
import elements.MovieCollection;

import java.io.Serial;
import java.io.Serializable;

public class Clear implements Command, Serializable {
    @Override
    public Message execute(MovieCollection movieCollection, Object object) {
        movieCollection.clear();
        return new Message(
                false,
                "Collection successfully cleared"
        );
    }

    @Serial
    private static final long serialVersionUID = -717981351376024031L;
}
