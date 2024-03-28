package servercommunication;

import java.io.Serial;
import java.io.Serializable;

public class CommandPackage<T> implements Serializable {
    public CommandPackage(CommandsList command, T argument) {
        this.command = command;
        this.argument = argument;
    }
    public final T argument;
    public final CommandsList command;
    @Serial
    private static final long serialVersionUID = -2521921947377787880L;
}
