package clientcommunication;

import java.io.Serial;
import java.io.Serializable;

public class RequestPackage<T> implements Serializable {
    public RequestPackage(CommandType command, T argument) {
        this.command = command;
        this.argument = argument;
    }
    public final T argument;
    public final CommandType command;
    @Serial
    private static final long serialVersionUID = -2521921947377787880L;
}
