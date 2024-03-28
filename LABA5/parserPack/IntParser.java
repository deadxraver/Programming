package parserPack;

import exceptionsPack.NullFieldException;

public class IntParser {
    public static int parse(String line) throws NumberFormatException, NullFieldException {
        if (line.isEmpty()) throw new NullFieldException();
        line = line.replaceAll("_", "");
        return Integer.parseInt(line);
    }
}
