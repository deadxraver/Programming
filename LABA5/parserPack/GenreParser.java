package parserPack;

import elementsPack.MovieGenre;
import exceptionsPack.NullFieldException;

public class GenreParser {
    public static MovieGenre parse(String line) throws NullFieldException {
        line = line.toLowerCase();
        if (line.isEmpty()) throw new NullFieldException();
        return line.equals("drama") ? MovieGenre.DRAMA
                : line.equals("fantasy") ? MovieGenre.FANTASY
                : line.equals("tragedy") ? MovieGenre.TRAGEDY
                : line.equals("western") ? MovieGenre.WESTERN
                : null;
    }
}
