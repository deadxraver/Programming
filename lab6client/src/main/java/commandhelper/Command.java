package commandhelper;

import elements.MovieCollection;
import servercommunication.Message;


@FunctionalInterface
public interface Command {
    Message execute(MovieCollection movieCollection, Object object);
}
