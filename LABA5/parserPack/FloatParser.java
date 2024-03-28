package parserPack;

import exceptionsPack.NullFieldException;

public class FloatParser {
    public static Float parse(String line) throws NumberFormatException, NullFieldException {
        if (line.isEmpty()) throw new NullFieldException();
        line = line.replaceAll("_", "").replaceAll(",", ".");
        return Float.parseFloat(line);
    }
}
