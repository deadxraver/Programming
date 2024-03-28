package servercommunication;

import java.io.Serial;
import java.io.Serializable;

public class ResponsePackage<T> implements Serializable {
    public ResponsePackage(T response, boolean errorsOccurred) {
        this.response = response;
        this.errorsOccurred = errorsOccurred;
    }
    public final T response;
    public final boolean errorsOccurred;
    @Serial
    private static final long serialVersionUID = 2460314300956698375L;
}
