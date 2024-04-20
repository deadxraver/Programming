package communication;

import commandhelper.Message;

import java.io.Serial;
import java.io.Serializable;

public record ResponsePackage(Message response) implements Serializable {
    @Serial
    private static final long serialVersionUID = 2460314300956698375L;
}
