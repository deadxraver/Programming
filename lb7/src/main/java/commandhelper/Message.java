package commandhelper;

import java.io.Serial;
import java.io.Serializable;

public record Message(boolean errorsOccurred, String message) implements Serializable {
    @Serial
    private static final long serialVersionUID = 8189089369140662809L;
}
