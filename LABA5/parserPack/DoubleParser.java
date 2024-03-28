package parserPack;

import exceptionsPack.NullFieldException;
import exceptionsPack.NumberOutOfBoundsException;

public class DoubleParser {
    public static final int UPPER_BOUND = 274;
    public static Double parse(String line) throws NullFieldException, NumberOutOfBoundsException {
        Double n = (Double)(double)(float)FloatParser.parse(line);
        if (n > UPPER_BOUND) throw new NumberOutOfBoundsException();
        return n;
    }
}
