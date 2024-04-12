package exceptions;

public class EmptyCollectionException extends Exception {
    @Override
    public String getMessage() {
        return "Collection is empty";
    }
}
