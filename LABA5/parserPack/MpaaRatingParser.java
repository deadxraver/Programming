package parserPack;

import elementsPack.MpaaRating;
import exceptionsPack.IncorrectInputException;
import exceptionsPack.NullFieldException;

public class MpaaRatingParser {
    public static MpaaRating parse(String line) throws IncorrectInputException {
        if (line.isEmpty()) return null;
        line = line.toUpperCase().replaceAll("-", "_");
        MpaaRating mpaaRating = line.equals("NC_17") ? MpaaRating.NC_17
                : line.equals("PG") ? MpaaRating.PG
                : line.equals("R") ? MpaaRating.R
                : null;
        if (mpaaRating == null) throw new IncorrectInputException();
        return mpaaRating;
    }
}
