package exceptions;

public class NullFieldException extends Exception {
    @Override
    public String getMessage() {
        return "Field cannot be null";
    }
}
