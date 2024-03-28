package clientcommunication;

import java.io.Serial;
import java.io.Serializable;

public class ResponsePackage<T> implements Serializable {
    public ResponsePackage(T response) {
        this.response = response;
    }
    public final T response;
    @Serial
    private static final long serialVersionUID = 2460314300956698375L;
}
