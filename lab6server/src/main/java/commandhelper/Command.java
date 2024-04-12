package commandhelper;

import elements.Movie;
import elements.MovieCollection;


@FunctionalInterface
public interface Command {
    Message execute(MovieCollection movieCollection, Object object);
}
