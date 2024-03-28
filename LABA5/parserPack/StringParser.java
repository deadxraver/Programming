package parserPack;

import exceptionsPack.EmptyStringException;

public class StringParser {
    public static String parse(String line) throws EmptyStringException {
        StringBuilder builder = new StringBuilder(line);
        do {
            if (builder.lastIndexOf(" ") == builder.length() - 1) {
                builder.deleteCharAt(builder.length() - 1);
            }
            if (builder.indexOf(" ") == 0) {
                builder.deleteCharAt(0);
            }
            if (builder.indexOf("  ") != -1) {
                builder.deleteCharAt(builder.indexOf("  "));
            }
        } while (builder.lastIndexOf(" ") == builder.length() - 1 || builder.indexOf(" ") == 0 || builder.indexOf("  ") != -1);
        if (builder.isEmpty()) throw new EmptyStringException();
        return builder.toString();
    }
}
