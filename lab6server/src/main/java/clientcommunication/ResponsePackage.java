package clientcommunication;

import java.io.Serial;
import java.io.Serializable;

public record ResponsePackage<T>(T response) implements Serializable {
    @Serial
    private static final long serialVersionUID = 2460314300956698375L;
}
