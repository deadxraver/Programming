package commandhelper;

import elements.MovieCollection;


@FunctionalInterface
public interface Command {
    Message execute(MovieCollection movieCollection, Object object);
}
