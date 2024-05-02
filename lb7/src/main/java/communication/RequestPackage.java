package communication;

import commandhelper.Command;

import java.io.Serial;
import java.io.Serializable;

public record RequestPackage<T>(Command command, T argument) implements Serializable {
    @Override
    public String toString() {
        return "command: " + command +
                ", argument: " + argument;
    }
    @Serial
    private static final long serialVersionUID = -2521921947377787880L;
}
